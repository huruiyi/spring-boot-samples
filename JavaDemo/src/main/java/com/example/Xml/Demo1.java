package com.example.Xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;


public class Demo1 {
    public static void main(String[] args) {
        try {
            // 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            document.setXmlStandalone(true);
            Element bookstore = document.createElement("claimReportInfo");

            Element book = document.createElement("policyNo");
            book.setTextContent("AHYXAGD24118E0004516");
            bookstore.appendChild(book);


            document.appendChild(bookstore);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();

            StringWriter writer=new StringWriter();

            tf.transform(new DOMSource(document), new StreamResult(writer));
            System.out.println(writer);
            //tf.transform(new DOMSource(document), new StreamResult(new File("book1---huhu.xml")));
            //System.out.println("生成book1.xml成功");
        } catch (Exception ex) {

        }

    }
}
