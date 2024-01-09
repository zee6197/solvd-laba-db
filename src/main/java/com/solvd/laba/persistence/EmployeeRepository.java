package com.solvd.laba.persistence;

import com.solvd.laba.domain.Employee;
import java.util.List;

public interface EmployeeRepository {

    void create(Employee employee);
    Employee findById(Long id);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(Long id);
}