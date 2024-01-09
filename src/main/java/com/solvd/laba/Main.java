package com.solvd.laba;

import com.solvd.laba.domain.Client;
import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.Project;
import com.solvd.laba.service.impl.ClientServiceImpl;
import com.solvd.laba.service.impl.DepartmentServiceImpl;
import com.solvd.laba.service.impl.EmployeeServiceImpl;
import com.solvd.laba.service.impl.EquipmentServiceImpl;
import com.solvd.laba.service.impl.ProjectServiceImpl;

public class Main {

    public static void main(String[] args) {

        Project project = new Project();
        Equipment equipment = new Equipment();
        Department department = new Department();
        Client client = new Client();
        Employee employee = new Employee();

        // Service instances
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        ClientServiceImpl clientService = new ClientServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

        // Create operations
        projectService.create(project);
        equipmentService.create(equipment);
        departmentService.create(department);
        clientService.create(client);
        employeeService.create(employee);

        // Retrieve operations
        Project retrievedProject = projectService.retrieveById(1L);
        System.out.println("Retrieved Project: " + retrievedProject);

        Equipment retrievedEquipment = equipmentService.retrieveById(1L);
        System.out.println("Retrieved Equipment: " + retrievedEquipment);

        Department retrievedDepartment = departmentService.retrieveById(1L);
        System.out.println("Retrieved Department: " + retrievedDepartment);

        Client retrievedClient = clientService.retrieveById(1L);
        System.out.println("Retrieved Client: " + retrievedClient);

        Employee retrievedEmployee = employeeService.retrieveById(1L);
        System.out.println("Retrieved Employee: " + retrievedEmployee);

        // Update operations
        projectService.update(retrievedProject);
        equipmentService.update(retrievedEquipment);
        departmentService.update(retrievedDepartment);
        clientService.update(retrievedClient);
        employeeService.update(retrievedEmployee);

        // Delete operations
        projectService.delete(1L);
        equipmentService.delete(1L);
        departmentService.delete(1L);
        clientService.delete(1L);
        employeeService.delete(1L);
    }
}
