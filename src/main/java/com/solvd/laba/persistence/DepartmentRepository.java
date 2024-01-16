package com.solvd.laba.persistence;

import com.solvd.laba.domain.Department;

import java.util.List;

public interface DepartmentRepository {

    void create(Department department, Long companyID);
    Department findById(Long id);
    List<Department> findAll();
    void update(Department department);
    void delete(Long id);
}