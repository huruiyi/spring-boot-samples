package com.example.bean;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportExcelUtil<T> {

    List<ExcelCellField> cellFieldInfos = new ArrayList<>();

    private Boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public List<T> readFile(MultipartFile multipartFile, Class<T> t) {
        List<T> list = new ArrayList<>();

        try {
            XSSFWorkbook xssfWorkbook = null;
            HSSFWorkbook hssfWorkbook = null;

            Sheet sheet = null;
            Boolean isExcel2003 = isExcel2003(multipartFile.getOriginalFilename());
            if (isExcel2003) {
                hssfWorkbook = new HSSFWorkbook(multipartFile.getInputStream());
                sheet = hssfWorkbook.getSheetAt(0);
            } else {
                xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
                sheet = xssfWorkbook.getSheetAt(0);
            }

            Row row0 = sheet.getRow(0);


            getCellFieldInfos(t, row0);

            for (int i = sheet.getFirstRowNum() + 1; i < sheet.getLastRowNum() + 1; i++) {
                T objData = t.newInstance();

                Row row = sheet.getRow(i);
                for (int j = row0.getFirstCellNum(); j < row0.getLastCellNum(); j++) {

                    Cell cell = row.getCell(j);
                    ExcelCellField excelCellField = getCellFieldInfo(j);
                    excelCellField.setRowIndex(i);

                    if (StringUtils.isNoneBlank(excelCellField.getFieldName())) {
                        Field field = objData.getClass().getDeclaredField(excelCellField.getFieldName());
                        field.setAccessible(true);
                        setFiledValue(field, objData, cell, excelCellField);
                    }
                }
                list.add(objData);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    /**
     * T 映射字段名和字段描述
     *
     * @param t
     * @param row0
     */
    private void getCellFieldInfos(Class<T> t, Row row0) {

        short firstCellNum = row0.getFirstCellNum();
        short lastCellNum = row0.getLastCellNum();

        ExcelCellField cellFieldInfo;

        for (int i = firstCellNum; i < lastCellNum; i++) {
            cellFieldInfo = new ExcelCellField();
            cellFieldInfo.setCellIndex(i);
            cellFieldInfo.setCellDescription(row0.getCell(i).getStringCellValue());

            cellFieldInfos.add(cellFieldInfo);
        }

        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            String cellDescription = "";

            String fieldName = field.getName();
            String dateFormat = "";
            Integer precision = 1;
            boolean isErrorInfoField = false;

            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (ExcelColumn.class == annotation.annotationType()) {
                    ExcelColumn columnInfo = (ExcelColumn) annotation;
                    cellDescription = columnInfo.desc();
                    dateFormat = columnInfo.dateFormat();
                    precision = columnInfo.precision();
                    isErrorInfoField = columnInfo.errorInfoField();
                }
            }

            for (ExcelCellField fieldInfo : cellFieldInfos) {
                if (fieldInfo.getCellDescription().equals(cellDescription)) {
                    fieldInfo.setFieldName(fieldName);
                    fieldInfo.setDateFormat(dateFormat);
                    fieldInfo.setPrecision(precision);

                }
            }
            //错误列设置
            if (isErrorInfoField) {
                ExcelCellField excelCellField = new ExcelCellField();
                excelCellField.setFieldName(fieldName);
                excelCellField.setErrorFiledFlag(true);
                cellFieldInfos.add(excelCellField);
            }
        }
    }

    private Object getEntityMemberValue(String type, String cellValue, ExcelCellField excelCellField) {
        Object realValue = null;
        try {
            switch (type) {
                case "char":
                case "java.lang.Character":
                case "java.lang.String":
                    realValue = StringUtils.removeEnd(cellValue, ".0");
                    break;
                case "java.util.Date":
                    realValue = StringUtils.isBlank(cellValue) ? null : strToDate(cellValue, excelCellField.getDateFormat());
                    break;

                case "int":
                case "java.lang.Integer":
                    realValue = StringUtils.isBlank(cellValue) ? null : Integer.parseInt(StringUtils.removeEnd(cellValue, ".0"));
                    break;
                case "java.lang.Short":
                    realValue = StringUtils.isBlank(cellValue) ? null : Short.parseShort(StringUtils.removeEnd(cellValue, ".0"));
                    break;
                case "java.lang.Long":
                    realValue = StringUtils.isBlank(cellValue) ? null : Long.parseLong(StringUtils.removeEnd(cellValue, ".0"));
                    break;
                case "float":
                case "java.lang.Float":
                    float floatVal = StringUtils.isBlank(cellValue) ? null : Float.parseFloat(StringUtils.removeEnd(cellValue, ".0"));
                    BigDecimal b = new BigDecimal(floatVal);
                    realValue = b.setScale(excelCellField.getPrecision(), BigDecimal.ROUND_HALF_UP).floatValue();
                    break;

                case "double":
                case "java.lang.Double":
                    double doubleVal = StringUtils.isBlank(cellValue) ? null : Double.parseDouble(StringUtils.removeEnd(cellValue, ".0"));
                    BigDecimal db = new BigDecimal(doubleVal);
                    realValue = db.setScale(excelCellField.getPrecision(), BigDecimal.ROUND_HALF_UP).doubleValue();
                    break;

                case "java.math.BigDecimal":
                    realValue = StringUtils.isBlank(cellValue) ? null : (new BigDecimal(cellValue)).setScale(excelCellField.getPrecision(), BigDecimal.ROUND_HALF_UP);
                    break;
                case "java.lang.Byte":
                    realValue = Byte.valueOf(StringUtils.removeEnd(cellValue, ".0"));
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            ExcelErrorInfo errorInfo = new ExcelErrorInfo();
            errorInfo.setCellIndex(excelCellField.getCellIndex());
            errorInfo.setRowIndex(excelCellField.getRowIndex());
            errorInfo.setHeadTitle(excelCellField.getCellDescription());
            errorInfo.setMessage(ex.getMessage());

            realValue = errorInfo;
        }
        return realValue;
    }

    public static Date strToDate(String strDate, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
        }
        return date;
    }

    private void setFiledValue(Field field, Object obj, Cell cell, ExcelCellField excelCellField)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        CellType cellType = cell.getCellType();

        String cellValue = "";
        switch (cellType) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                if (cellValue.indexOf("E") >= 0) {
                    NumberFormat nf = NumberFormat.getInstance();
                    String s = nf.format(cell.getNumericCellValue());
                    if (s.indexOf(",") >= 0) {
                        s = s.replace(",", "");
                    }
                    cellValue = s;
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                cellValue = "";
                break;
        }

        Object entityMemberValue = getEntityMemberValue(field.getType().getName(), cellValue, excelCellField);

        Boolean isSetErrorInfo = false;
        //判断返回值类型-是否是错信息
        if (entityMemberValue.getClass().getTypeName().equals(ExcelErrorInfo.class.getTypeName())) {
            ExcelCellField errorInfoField = getErrorInfoField();
            if (errorInfoField != null) {
                isSetErrorInfo = true;

                Object property = PropertyUtils.getProperty(obj, errorInfoField.getFieldName());

                Field errorFiled = obj.getClass().getField(errorInfoField.getFieldName());
                List<ExcelErrorInfo> excelErrorInfos = (List<ExcelErrorInfo>) errorFiled.get(obj);
                excelErrorInfos.add(((ExcelErrorInfo) entityMemberValue));
                errorFiled.set(obj, excelErrorInfos);
            }
        }
        if (!isSetErrorInfo) {
            org.apache.commons.beanutils.PropertyUtils.setProperty(obj, field.getName(), entityMemberValue);
        }
    }

    private ExcelCellField getErrorInfoField() {
        for (ExcelCellField item : cellFieldInfos) {
            if (item.isErrorFiledFlag()) {
                return item;
            }

        }
        return null;
    }

    private ExcelCellField getCellFieldInfo(int index) {
        for (ExcelCellField item : cellFieldInfos) {
            if (item.getCellIndex() == index) {
                return item;
            }
        }
        return null;
    }
}
