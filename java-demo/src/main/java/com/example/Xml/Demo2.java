package com.example.Xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import java.util.List;

public class Demo2 {

    public static void main(String[] args) throws DocumentException {
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<claimReportInfoResponse>\n" +
                "  <registerNo>DHYXHYE24119000005</registerNo>\n" +
                "  <comments>报案成功</comments>\n" +
                "</claimReportInfoResponse>";

        Document doc = DocumentHelper.parseText(xml);
        List registerNo = doc.selectNodes("registerNo");

        String registerNo1 = doc.getRootElement().elementText("registerNo");
        System.out.println(registerNo1);
    }
}
