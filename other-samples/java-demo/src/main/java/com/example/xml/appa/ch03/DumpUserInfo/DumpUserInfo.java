package com.example.xml.appa.ch03.DumpUserInfo;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static java.lang.System.err;
import static java.lang.System.out;

public class DumpUserInfo {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("tomcat-users.xml");
            NodeList nl = doc.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    dump((Element) node);
                }
            }
        } catch (IOException ioe) {
            err.printf("IOE: %s%n", ioe.toString());
        } catch (SAXException saxe) {
            err.printf("SAXE: %s%n", saxe.toString());
        } catch (FactoryConfigurationError fce) {
            err.printf("FCE: %s%n", fce.toString());
        } catch (ParserConfigurationException pce) {
            err.printf("PCE: %s%n", pce.toString());
        }
    }

    static void dump(Element e) {
        if (e.getNodeName().equals("user")) {
            NamedNodeMap nnm = e.getAttributes();
            if (nnm != null) {
                for (int i = 0; i < nnm.getLength(); i++) {
                    Node node = nnm.item(i);
                    Attr attr =
                            e.getAttributeNode(node.getNodeName());
                    out.printf("%s = %s%n", attr.getName(),
                            attr.getValue());
                }
            }
            out.println();
        }
        NodeList nl = e.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                dump((Element) node);
            }
        }
    }
}
