package com.solvd.laba;

import com.solvd.laba.domain.*;
import com.solvd.laba.service.EquipmentService;
import com.solvd.laba.service.impl.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Top of the hierarchy:
        BuildingCompanyServiceImpl buildingCompanyService = new BuildingCompanyServiceImpl();

        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        ClientServiceImpl clientService = new ClientServiceImpl();



        // Create operations

        ArrayList<Client> clients = new ArrayList<>();
        Client newClient = new Client();
        newClient.setName("Acme Corp");
        newClient.setContactInfo("acme@corpmail.com");
        newClient.setIndustry("Construction");
        clients.add(newClient);

        BuildingCompany newBuildingCompany = new BuildingCompany();
        newBuildingCompany.setName("Skyscraper In");
        newBuildingCompany.setLocation("Metropolis");
        buildingCompanyService.create(newBuildingCompany);


        Department newDepartment = new Department();
        newDepartment.setName("Engineering");
        // newDepartment.getEmployees();


        Equipment newEquipment = new Equipment();
        newEquipment.setName("Crane");
        newEquipment.setEquipmentType("Heavy Machinery");



        // Update operations

        buildingCompanyService.update(newBuildingCompany);

        clientService.update(newClient);

        // equipmentService.update(newEquipment);
        // departmentService.update(newDepartment);
        // employeeService.update(newEmployee);


    }
}