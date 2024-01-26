package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Department;
import com.solvd.laba.persistence.DepartmentRepository;
import com.solvd.laba.persistence.impl.DepartmentDAO;
import com.solvd.laba.persistence.mybatis.DepartmentMyBatisDAO;
import com.solvd.laba.service.DepartmentService;
import com.solvd.laba.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;



    public DepartmentServiceImpl() {

        this.departmentRepository = new DepartmentDAO();
        this.employeeService = new EmployeeServiceImpl();

    }

    @Override
    public void create(Department department, Long companyID) {
        LOGGER.info("Creating department");
        departmentRepository.create(department, companyID);

        if (!department.getEmployees().isEmpty()) {
            department.getEmployees().forEach(employee -> {
                employeeService.create(employee, department.getId());
            });
        }
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

    @Override
    public Department retrieveWithEmployeesById(Long id) {
        LOGGER.info("Retrieving department with employees by id");
        return departmentRepository.findById(id);
    }
}