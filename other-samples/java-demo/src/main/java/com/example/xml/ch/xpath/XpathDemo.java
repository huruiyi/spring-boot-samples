package com.example.xml.ch.xpath;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class XpathDemo {

    private static final Logger log = LoggerFactory.getLogger(Dom4jTest.class);

    public static void main(String[] args) throws DocumentException, ParserConfigurationException, IOException, SAXException {
        String dir = System.getProperty("user.dir");
        log.info(dir);

        String path = XpathDemo.class.getClassLoader().getResource("xml/web.xml").getPath();//注意getResource("")里面是空字符串
        log.info(path);
        Document document = new SAXReader().read(path);

        List<Node> nodes = document.selectNodes("/web-app/servlet/servlet-name");
        for (Node node : nodes) {
            log.info(node.getName() + "  " + node.getText());
        }

        Element ele = (Element) document.selectSingleNode("//servlet/servlet-name");
        log.info(ele.getText());

        log.info("*****************************************************");
        List<Element> list = document.getRootElement().elements();
        for (Element element : list) {
            log.info(element.elementText("servlet-name"));

            log.info(element.elementText("url-pattern"));
            log.info(element.elementText("servlet-class"));
        }

        String value = document.getRootElement().attributeValue("version");
        log.info(value);


        Element servletClass = (Element) document.selectSingleNode("//servlet-class");
        Element urlPattern = (Element) document.selectSingleNode("//url-pattern");

        String classText = servletClass.getText();
        String urlText = urlPattern.getText();
        log.info(urlText + "-----------" + classText);
    }

}
