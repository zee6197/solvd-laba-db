package com.solvd.laba.parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomParser {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/main/resources/parsers/xml/buildingCompany.xml");
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            System.out.println("ID: " + getElementValue(root, "id"));
            System.out.println("Name: " + getElementValue(root, "name"));
            System.out.println("Location: " + getElementValue(root, "location"));
            NodeList departments = root.getElementsByTagName("department");
            processDepartments(departments);
            NodeList clients = root.getElementsByTagName("client");
            processClients(clients);
            NodeList equipment = root.getElementsByTagName("equipmentItem");
            processEquipment(equipment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processDepartments(NodeList departments) {
        for (int i = 0; i < departments.getLength(); i++) {
            Element department = (Element) departments.item(i);
            System.out.println("\nDepartment ID: " + getElementValue(department, "id"));
            System.out.println("Department Name: " + getElementValue(department, "name"));

            NodeList employees = department.getElementsByTagName("employee");
            for (int j = 0; j < employees.getLength(); j++) {
                Element employee = (Element) employees.item(j);
                System.out.println("  Employee ID: " + getElementValue(employee, "id"));
                System.out.println("  Employee First Name: " + getElementValue(employee, "firstName"));
            }
        }
    }

    private static void processClients(NodeList clients) {
        for (int i = 0; i < clients.getLength(); i++) {
            Element client = (Element) clients.item(i);
            System.out.println("\nClient ID: " + getElementValue(client, "id"));
            System.out.println("Client Name: " + getElementValue(client, "name"));
        }
    }

    private static void processEquipment(NodeList equipment) {
        for (int i = 0; i < equipment.getLength(); i++) {
            Element equipmentItem = (Element) equipment.item(i);
            System.out.println("\nEquipment ID: " + getElementValue(equipmentItem, "id"));
            System.out.println("Equipment Name: " + getElementValue(equipmentItem, "name"));
        }
    }

    private static String getElementValue(Element parent, String label) {
        NodeList nodeList = parent.getElementsByTagName(label);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        } else {
            return null;
        }
    }
}
