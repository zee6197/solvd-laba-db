package com.solvd.laba.parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import com.solvd.laba.parsers.model.BuildingCompany;

public class DomParser {
    public static void main(String[] args) {

        File file = new File("src/main/resources/parsers/xml/buildingCompany.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            // BuildingCompany
            BuildingCompany buildingCompany = new BuildingCompany();
            buildingCompany.setId(Long.parseLong(getElementValue(root, "id")));
            buildingCompany.setName(getElementValue(root, "name"));
            buildingCompany.setLocation(getElementValue(root, "location"));
            System.out.println(buildingCompany);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }
}
