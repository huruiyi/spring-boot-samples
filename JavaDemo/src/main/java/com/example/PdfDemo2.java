package com.example;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：PdfDemo2
 * 日期：2019/4/26 16:16
 **/
public class PdfDemo2 {
    public static void main(String[] args) throws IOException, DocumentException {
        PdfReader reader = new PdfReader("Personal-Info.pdf");

        FileOutputStream fileOutputStream = new FileOutputStream("PersonalInfo-DEST.pdf");
        PdfStamper pdfStamper = new PdfStamper(reader, fileOutputStream); // 生成的输出流
        AcroFields fields = pdfStamper.getAcroFields();
        Map fieldMap = fields.getFields(); // pdf表单相关信息展示

        Set<Map.Entry<String, AcroFields.Item>> entrySet = fields.getFields().entrySet();

        for (Map.Entry<String, AcroFields.Item> entry : entrySet) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

     /*   for (Map.Entry entry : fieldMap.entrySet()) {
            String name = entry.getKey(); // name就是pdf模版中各个文本域的名字
            AcroFields.Item item = (Item) entry.getValue();
            System.out.println("[name]:" + name + ", [value]: " + item);
        }*/

        fields.setField("nation", "中国");
        fields.setField("name", "胡睿毅");

        pdfStamper.close();
        fileOutputStream.close();
    }
}
