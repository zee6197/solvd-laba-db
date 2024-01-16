package com.solvd.laba;

import com.solvd.laba.domain.*;
import com.solvd.laba.service.impl.*;

public class Main {

    public static void main(String[] args) {


        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
//        ClientServiceImpl clientService = new ClientServiceImpl();
//        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
//        CredentialServiceImpl credentialService = new CredentialServiceImpl();
//        BuildingCompanyServiceImpl buildingCompanyService = new BuildingCompanyServiceImpl();


        // Create operations
        equipmentService.create(new Equipment());
//        departmentService.create(new Department());
//        clientService.create(new Client());
//        employeeService.create(new Employee());
//        credentialService.create(new Credential());
//        buildingCompanyService.create(new BuildingCompany());

        // Retrieve operations
        Equipment retrievedEquipment = equipmentService.retrieveById(1L);
//        Department retrievedDepartment = departmentService.retrieveById(1L);
//        Client retrievedClient = clientService.retrieveById(1L);
//        Employee retrievedEmployee = employeeService.retrieveById(1L);
//        Credential retrievedCredential = credentialService.retrieveById(1L);
//        BuildingCompany retrievedBuildingCompany = buildingCompanyService.retrieveById(1L);

        System.out.println("Retrieved Equipment: " + retrievedEquipment);
//        System.out.println("Retrieved Department: " + retrievedDepartment);
//        System.out.println("Retrieved Client: " + retrievedClient);
//        System.out.println("Retrieved Employee: " + retrievedEmployee);
//        System.out.println("Retrieved Credential: " + retrievedCredential);
//        System.out.println("Retrieved Building Company: " + retrievedBuildingCompany);


        // Update operations
        equipmentService.update(retrievedEquipment);
//        departmentService.update(retrievedDepartment);
//        clientService.update(retrievedClient);
//        employeeService.update(retrievedEmployee);
//        credentialService.update(retrievedCredential);
//        buildingCompanyService.update(retrievedBuildingCompany);

        // Delete operations
        equipmentService.delete(retrievedEquipment.getId());
//        departmentService.delete(retrievedDepartment.getId());
//        clientService.delete(retrievedClient.getId());
//        employeeService.delete(retrievedEmployee.getId());
//        credentialService.delete(retrievedCredential.getId());
//        buildingCompanyService.delete(retrievedBuildingCompany.getId());


        // Create operations
        Equipment newEquipment = new Equipment();
        newEquipment.setName("Drill");
        newEquipment.setEquipmentType("Heavy");
        equipmentService.create(newEquipment);

//        Department newDepartment = new Department();
//        newDepartment.setName("Development");
//        departmentService.create(newDepartment);
//
//        Client newClient = new Client();
//        newClient.setName("Acme Corp");
//        newClient.setContactInfo("acme@corpmail.com");
//        newClient.setIndustry("Construction");
//        clientService.create(newClient);
//
//        Employee newEmployee = new Employee();
//        newEmployee.setFirstName("John");
//        newEmployee.setLastName("Doe");
//        newEmployee.setHireDate(new java.util.Date());
//        newEmployee.setSalary(75000.00);
//        employeeService.create(newEmployee);
//
//        Credential newCredential = new Credential();
//        newCredential.setLogin("jdoe");
//        newCredential.setPassword("pass123");
//        credentialService.create(newCredential);

//        BuildingCompany newBuildingCompany = new BuildingCompany();
//        newBuildingCompany.setName("Skyscraper Inc.");
//        newBuildingCompany.setLocation("Metropolis");
//        buildingCompanyService.create(newBuildingCompany);

    }
}
