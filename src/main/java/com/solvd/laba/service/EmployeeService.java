package com.solvd.laba.service;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Credential;
import java.util.List;

public interface EmployeeService {

    void create(Employee employee);
    Employee retrieveById(Long id);
    List<Employee> retrieveAll();
    void update(Employee employee);
    void delete(Long id);
    // Method to retrieve an employee with their credentials
    Employee retrieveWithCredentialsById(Long id);
}