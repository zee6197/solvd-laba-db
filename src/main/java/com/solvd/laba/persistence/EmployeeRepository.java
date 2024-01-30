package com.solvd.laba.persistence;

import com.solvd.laba.domain.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface EmployeeRepository {

    void create(@Param("employee") Employee employee, @Param("departmentID") Long departmentID);
    Employee findById(Long id);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(Long id);

}
