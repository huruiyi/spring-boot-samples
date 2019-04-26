package com.example.springbootdemo.web;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：HelloController
 * 日期：2019/4/26 8:44
 **/
@RestController
public class HelloController {

    public XSSFWorkbook getOutputStream() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        try {

            //0:jar包读取：
            String jasperFilePath = "classpath:templates/report/paymentDetails.xlsx";
            org.springframework.core.io.Resource resource = new DefaultResourceLoader().getResource(jasperFilePath);//通过这个来加载,当项目打包成jar也可以
            workbook = new XSSFWorkbook(resource.getInputStream());

            //1:jar包读取：
            //ClassPathResource resource = new ClassPathResource("templates/report/paymentDetails.xlsx");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            //workbook = new XSSFWorkbook(resource.getInputStream());

            //2:本地运行
            //File file = ResourceUtils.getFile("classpath:templates/report/paymentDetails.xlsx");
            //workbook = new XSSFWorkbook(new FileInputStream(file));

            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = null;
            //从第五行开始写数据
            //序号	账户类型	进账	支出	本次余额	订单号	订单金额	流水号	备注	对应用户	操作时间	水单	CBS处理
            XSSFDataFormat dataFormat = workbook.createDataFormat();
            for (int i = 1; i <= 1; i++) {
                row = sheet.createRow(4 + i);
                for (int j = 0; j < 13; j++) {
                    XSSFCellStyle cellStyle = workbook.createCellStyle();
                    XSSFCell cell = row.createCell(j);
                    Font font = workbook.createFont();
                    font.setFontName("微软雅黑");
                    if (j == 0) {
                        cell.setCellValue(i);
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    } else if (j == 1) {
                        cell.setCellValue("账户类型");
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    } else if (j == 2) {
                        BigDecimal incomeAmt = new BigDecimal(12345.56);
                        if (incomeAmt.compareTo(BigDecimal.ZERO) < 0) {
                            font.setColor(Font.COLOR_RED);
                        }
                        cellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
                        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                        cell.setCellValue(incomeAmt.doubleValue());
                    } else if (j == 3) {
                        BigDecimal expenditureAmt = new BigDecimal(-1245.55);
                        if (expenditureAmt.compareTo(BigDecimal.ZERO) < 0) {
                            font.setColor(Font.COLOR_RED);
                        }
                        cellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
                        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                        cell.setCellValue(expenditureAmt.doubleValue());
                    } else if (j == 4) {
                        //本次余额
                        BigDecimal balance = new BigDecimal(1245);
                        cellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
                        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                        cell.setCellValue(balance.doubleValue());
                    } else if (j == 5) {
                        //订单号
                    } else if (j == 6) {
                        //订单金额
                    } else if (j == 7) {
                        //流水号
                    } else if (j == 8) {
                        //备注
                    } else if (j == 9) {
                        //对应用户
                    } else if (j == 10) {
                        //操作时间
                    } else if (j == 11) {
                        //水单
                    } else if (j == 12) {
                        //CBS处理
                    }
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    @RequestMapping(value = "/test")
    public void test(HttpServletResponse response) throws UnsupportedEncodingException {

        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");

        XSSFWorkbook xssfWorkbook = getOutputStream();

        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhhmmss");
            String fileName = sd.format(new Date()) + "预付款明细.xlsx";
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel");
            xssfWorkbook.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (xssfWorkbook != null) {
                    xssfWorkbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
