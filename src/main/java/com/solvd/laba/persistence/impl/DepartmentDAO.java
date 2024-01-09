package com.solvd.laba.persistence.impl;


import com.solvd.laba.domain.Department;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.DepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements DepartmentRepository {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO departments (name, building_company_id) VALUES (?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM departments WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM departments";
    private static final String UPDATE_QUERY = "UPDATE departments SET name = ?, building_company_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM departments WHERE id = ?";

    @Override
    public void create(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getBuildingCompany().getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating department failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    department.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating department failed, no ID obtained.");
                }
            }
            LOGGER.info("Department created: {}", department);
        } catch (SQLException e) {
            LOGGER.error("Unable to create department: ", e);
            throw new RuntimeException("Unable to create Department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Department findById(Long id) {
        Department department = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find department by id: ", e);
            throw new RuntimeException("Unable to find Department by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                departments.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find all departments: ", e);
            throw new RuntimeException("Unable to find all Departments", e);
        }
        return departments;
    }

    @Override
    public void update(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getBuildingCompany().getId());
            preparedStatement.setLong(3, department.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating department failed, no rows affected.");
            }
            LOGGER.info("Department updated: {}", department);
        } catch (SQLException e) {
            LOGGER.error("Unable to update department: ", e);
            throw new RuntimeException("Unable to update Department", e);
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
                throw new SQLException("Deleting department failed, no rows affected.");
            }
            LOGGER.info("Department deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete department: ", e);
            throw new RuntimeException("Unable to delete Department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private Department mapRow(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setName(resultSet.getString("name"));
        return department;
    }
}
