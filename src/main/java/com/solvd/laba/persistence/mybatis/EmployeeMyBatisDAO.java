package com.solvd.laba.persistence.mybatis;

import com.solvd.laba.persistence.EmployeeRepository;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class EmployeeMyBatisDAO implements EmployeeRepository {

    @Override
    public void create(Employee employee, Long departmentID) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeDAO = sqlSession.getMapper(EmployeeRepository.class);
            employeeDAO.create(employee, departmentID);
        }
    }

    @Override
    public Employee findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeDAO = sqlSession.getMapper(EmployeeRepository.class);
            return employeeDAO.findById(id);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeDAO = sqlSession.getMapper(EmployeeRepository.class);
            return employeeDAO.findAll();
        }
    }

    @Override
    public void update(Employee employee) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeDAO = sqlSession.getMapper(EmployeeRepository.class);
            employeeDAO.update(employee);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeDAO = sqlSession.getMapper(EmployeeRepository.class);
            employeeDAO.delete(id);
        }
    }
}
