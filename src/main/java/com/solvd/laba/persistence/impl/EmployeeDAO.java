package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements EmployeeRepository {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO employees (first_name, last_name, hire_date, salary, credential_id, department_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM employees WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM employees";
    private static final String UPDATE_QUERY = "UPDATE employees SET first_name = ?, last_name = ?, hire_date = ?, salary = ?, credential_id = ?, department_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM employees WHERE id = ?";

    @Override
    public void create(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(employee.getHireDate().getTime()));
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setLong(5, employee.getCredentials().getId());
            preparedStatement.setLong(6, employee.getDepartment().getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating employee failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }
            LOGGER.info("Employee created: {}", employee);
        } catch (SQLException e) {
            LOGGER.error("Unable to create employee: ", e);
            throw new RuntimeException("Unable to create Employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    @Override
    public Employee findById(Long id) {
        Employee employee = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find employee by id: ", e);
            throw new RuntimeException("Unable to find Employee by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employee;
    }


    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to find all employees: ", e);
            throw new RuntimeException("Unable to find all Employees", e);
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(employee.getHireDate().getTime()));
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setLong(5, employee.getCredentials().getId());
            preparedStatement.setLong(6, employee.getDepartment().getId());
            preparedStatement.setLong(7, employee.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating employee failed, no rows affected.");
            }
            LOGGER.info("Employee updated: {}", employee);
        } catch (SQLException e) {
            LOGGER.error("Unable to update employee: ", e);
            throw new RuntimeException("Unable to update Employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting employee failed, no rows affected.");
            }
            LOGGER.info("Employee deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete employee: ", e);
            throw new RuntimeException("Unable to delete Employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    private Employee mapRow(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setHireDate(resultSet.getDate("hire_date"));
        employee.setSalary(resultSet.getDouble("salary"));
        return employee;
    }
}
