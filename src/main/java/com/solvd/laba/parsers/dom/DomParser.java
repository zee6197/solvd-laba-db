package com.solvd.laba.parsers.dom;

import com.solvd.laba.domain.Project;
import com.solvd.laba.parsers.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            BuildingCompany buildingCompany = parseBuildingCompany(root);
            System.out.println(buildingCompany);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static BuildingCompany parseBuildingCompany(Element root) {
        BuildingCompany company = new BuildingCompany();
        company.setId(Long.parseLong(getElementValue(root, "id")));
        company.setName(getElementValue(root, "name"));
        company.setLocation(getElementValue(root, "location"));
        company.setDepartment(parseDepartments(root));
        company.setClients(parseClients(root));
        company.setEquipment(parseEquipment(root));
        company.setProjects(parseProjects(root));
        return company;
    }

    private static List<Department> parseDepartments(Element root) {
        NodeList departmentNodes = root.getElementsByTagName("department");
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < departmentNodes.getLength(); i++) {
            Element deptElement = (Element) departmentNodes.item(i);
            Department department = new Department();
            department.setId(Long.parseLong(deptElement.getElementsByTagName("id").item(0).getTextContent()));
            department.setName(deptElement.getElementsByTagName("name").item(0).getTextContent());
            department.setEmployees(parseEmployees(deptElement));
            departments.add(department);
        }
        return departments;
    }

    private static List<Client> parseClients(Element root) {
        NodeList clientNodes = root.getElementsByTagName("client");
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < clientNodes.getLength(); i++) {
            Element clientElement = (Element) clientNodes.item(i);
            Client client = new Client();
            client.setId(Long.parseLong(clientElement.getElementsByTagName("id").item(0).getTextContent()));
            client.setName(clientElement.getElementsByTagName("name").item(0).getTextContent());
            clients.add(client);
        }
        return clients;
    }

    private static List<Equipment> parseEquipment(Element root) {
        NodeList equipmentNodes = root.getElementsByTagName("equipmentItem");
        List<Equipment> equipmentList = new ArrayList<>();
        for (int i = 0; i < equipmentNodes.getLength(); i++) {
            Element equipmentElement = (Element) equipmentNodes.item(i);
            Equipment equipment = new Equipment();
            equipment.setId(Long.parseLong(equipmentElement.getElementsByTagName("id").item(0).getTextContent()));
            equipment.setName(equipmentElement.getElementsByTagName("name").item(0).getTextContent());
            equipmentList.add(equipment);
        }
        return equipmentList;
    }

    private static List<Project> parseProjects(Element root) {
        NodeList projectNodes = root.getElementsByTagName("project");
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < projectNodes.getLength(); i++) {
            Element projectElement = (Element) projectNodes.item(i);
            Project project = new Project();
            project.setId(Long.parseLong(projectElement.getElementsByTagName("id").item(0).getTextContent()));
            projects.add(project);
        }
        return projects;
    }

    private static List<Employee> parseEmployees(Element departmentElement) {
        NodeList employeeNodes = departmentElement.getElementsByTagName("employee");
        List<Employee> employees = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Element empElement = (Element) employeeNodes.item(i);
            Employee employee = new Employee();
            employee.setId(Long.parseLong(empElement.getElementsByTagName("id").item(0).getTextContent()));
            employee.setFirstName(empElement.getElementsByTagName("firstName").item(0).getTextContent());
            employee.setLastName(empElement.getElementsByTagName("lastName").item(0).getTextContent());
            String hireDateString = empElement.getElementsByTagName("hireDate").item(0).getTextContent();
            try {
                Date hireDate = dateFormat.parse(hireDateString);
                employee.setHireDate(hireDate);
            } catch (ParseException e) {
                System.err.println("Error parsing hire date: " + hireDateString);
                e.printStackTrace();
            }
            employee.setSalary(Double.parseDouble(empElement.getElementsByTagName("salary").item(0).getTextContent()));
            employees.add(employee);
        }
        return employees;
    }

    private static String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }
}
