package com.solvd.laba.service;

import com.solvd.laba.domain.Employee;
import java.util.List;

public interface EmployeeService {

    void create(Employee employee);
    Employee retrieveById(Long id);
    List<Employee> retrieveAll();
    void update(Employee employee);
    void delete(Long id);
}
