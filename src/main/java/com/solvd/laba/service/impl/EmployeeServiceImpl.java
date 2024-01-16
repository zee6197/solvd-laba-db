package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.persistence.EmployeeRepository;
// import com.solvd.laba.persistence.impl.EmployeeDAO; // JDBC implementation
import com.solvd.laba.persistence.mybatis.EmployeeMyBatisDAO; // MyBatis implementation
import com.solvd.laba.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

    // MyBatis implementation
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        // JDBC implementation
        // this.employeeRepository = new EmployeeDAO();

        // MyBatis implementation
        this.employeeRepository = new EmployeeMyBatisDAO();
    }

    @Override
    public void create(Employee employee) {
        LOGGER.info("Creating employee");
        employeeRepository.create(employee);
    }

    @Override
    public Employee retrieveById(Long id) {
        LOGGER.info("Retrieving employee by id");
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> retrieveAll() {
        LOGGER.info("Retrieving all employees");
        return employeeRepository.findAll();
    }

    @Override
    public void update(Employee employee) {
        LOGGER.info("Updating employee");
        employeeRepository.update(employee);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting employee with ID: {}", id);
        employeeRepository.delete(id);
    }

    @Override
    public Employee retrieveWithCredentialsById(Long id) {
        LOGGER.info("Retrieving employee with credentials by id");
        return employeeRepository.findById(id);
    }
}
