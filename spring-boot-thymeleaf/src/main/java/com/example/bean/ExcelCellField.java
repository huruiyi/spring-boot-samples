package com.example.bean;

public class ExcelCellField {

    public int cellIndex;
    public int rowIndex;
    public String cellDescription;
    public String fieldName;
    public String dateFormat;
    public Integer precision;
    public boolean errorFiledFlag;


    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getCellDescription() {
        return cellDescription;
    }

    public void setCellDescription(String cellDescription) {
        this.cellDescription = cellDescription;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }


    public boolean isErrorFiledFlag() {
        return errorFiledFlag;
    }

    public void setErrorFiledFlag(boolean errorFiledFlag) {
        this.errorFiledFlag = errorFiledFlag;
    }

    @Override
    public String toString() {
        return "ExcelCellField{" +
                "cellIndex=" + cellIndex +
                ", rowIndex=" + rowIndex +
                ", cellDescription='" + cellDescription + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", precision=" + precision +
                ", errorFiledFlag=" + errorFiledFlag +
                '}';
    }
}
