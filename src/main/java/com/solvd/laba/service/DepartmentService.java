package com.solvd.laba.service;

import com.solvd.laba.domain.Department;
import java.util.List;

public interface DepartmentService {

    void create(Department department);
    Department retrieveById(Long id);
    List<Department> retrieveAll();
    void update(Department department);
    void delete(Long id);
}