package com.solvd.laba.persistence.impl;


import com.solvd.laba.domain.Project;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.ProjectRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements ProjectRepository {

    private static final Logger LOGGER = LogManager.getLogger(ProjectDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO projects (name, start_date, end_date, budget, building_company_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM projects WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM projects";
    private static final String UPDATE_QUERY = "UPDATE projects SET name = ?, start_date = ?, end_date = ?, budget = ?, building_company_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM projects WHERE id = ?";

    @Override
    public void create(Project project) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            preparedStatement.setDouble(4, project.getBudget());
            preparedStatement.setLong(5, project.getBuildingCompany().getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating project failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    project.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }
            LOGGER.info("Project created: {}", project);
        } catch (SQLException e) {
            LOGGER.error("Unable to create project: ", e);
            throw new RuntimeException("Unable to create Project", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Project findById(Long id) {
        Project project = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project = mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find project by id: ", e);
            throw new RuntimeException("Unable to find Project by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                projects.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find all projects: ", e);
            throw new RuntimeException("Unable to find all Projects", e);
        }
        return projects;
    }

    @Override
    public void update(Project project) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            preparedStatement.setDouble(4, project.getBudget());
            preparedStatement.setLong(5, project.getBuildingCompany().getId());
            preparedStatement.setLong(6, project.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating project failed, no rows affected.");
            }
            LOGGER.info("Project updated: {}", project);
        } catch (SQLException e) {
            LOGGER.error("Unable to update project: ", e);
            throw new RuntimeException("Unable to update Project", e);
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
                throw new SQLException("Deleting project failed, no rows affected.");
            }
            LOGGER.info("Project deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete project: ", e);
            throw new RuntimeException("Unable to delete Project", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private Project mapRow(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getLong("id"));
        project.setName(resultSet.getString("name"));
        project.setStartDate(resultSet.getDate("start_date"));
        project.setEndDate(resultSet.getDate("end_date"));
        project.setBudget(resultSet.getDouble("budget"));
        return project;
    }
}
