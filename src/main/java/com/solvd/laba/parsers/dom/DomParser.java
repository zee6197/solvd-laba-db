package com.solvd.laba.parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomParser {
    public static void main(String[] args) {
        File file = new File("src/main/resources/buildingCompany.xml");

        // DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
