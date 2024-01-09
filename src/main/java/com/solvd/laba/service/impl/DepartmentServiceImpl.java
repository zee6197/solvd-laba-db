package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Department;
import com.solvd.laba.persistence.DepartmentRepository;
import com.solvd.laba.persistence.impl.DepartmentDAO;
import com.solvd.laba.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl() {
        this.departmentRepository = new DepartmentDAO();
    }

    @Override
    public void create(Department department) {
        LOGGER.info("Creating department");
        departmentRepository.create(department);
    }

    @Override
    public Department retrieveById(Long id) {
        LOGGER.info("Retrieving department by id");
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> retrieveAll() {
        LOGGER.info("Retrieving all departments");
        return departmentRepository.findAll();
    }

    @Override
    public void update(Department department) {
        LOGGER.info("Updating department");
        departmentRepository.update(department);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting department with ID: {}", id);
        departmentRepository.delete(id);
    }
}
