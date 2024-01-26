package com.solvd.laba;

import com.solvd.laba.domain.*;
import com.solvd.laba.service.impl.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // Services initialization
        BuildingCompanyServiceImpl buildingCompanyService = new BuildingCompanyServiceImpl();
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        CredentialServiceImpl credentialService = new CredentialServiceImpl();

        // Creating BuildingCompany
        BuildingCompany newBuildingCompany = new BuildingCompany();
        newBuildingCompany.setName("Mike's Building Company");
        newBuildingCompany.setLocation("Alberta, Canada");

        // Creating Client
        Client newClient = new Client();
        newClient.setName("Ace Stores Inc");
        newClient.setContactInfo("aceinc@corpmail.com");
        newClient.setIndustry("Construction");
        newBuildingCompany.getClients().add(newClient);

        // Creating Department
        Department newDepartment = new Department();
        newDepartment.setName("Engineering");
        newBuildingCompany.getDepartment().add(newDepartment);

        // Creating Equipment
        Equipment newEquipment = new Equipment();
        newEquipment.setName("Crane");
        newEquipment.setEquipmentType("Heavy machinery");
        newBuildingCompany.getEquipment().add(newEquipment);

        // Creating Credential
        Credential credential = new Credential();
        String uniqueLogin = "john" + new Date().getTime();
        credential.setLogin(uniqueLogin);
        credential.setPassword("password123");
        credentialService.create(credential);

        Employee newEmployee = null;
        if (credential != null && credential.getId() != null) {
            // Creating Employee
            newEmployee = new Employee();
            newEmployee.setFirstName("Mike");
            newEmployee.setLastName("Smith");
            newEmployee.setHireDate(new Date());
            newEmployee.setSalary(5000.00);
            newEmployee.setCredentials(credential);
            newDepartment.getEmployees().add(newEmployee);
        } else {
            System.out.println("Failed to create credentials for the employee.");
        }

        // Creating the BuildingCompany
        buildingCompanyService.create(newBuildingCompany);
    }
}
