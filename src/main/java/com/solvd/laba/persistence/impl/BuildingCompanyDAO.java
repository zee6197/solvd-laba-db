package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.BuildingCompany;
import com.solvd.laba.domain.Client;
import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.persistence.BuildingCompanyRepository;
import com.solvd.laba.persistence.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildingCompanyDAO implements BuildingCompanyRepository {

    private static final Logger LOGGER = LogManager.getLogger(BuildingCompanyDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO building_company (name, location) VALUES (?, ?)";
    private static final String SELECT_BY_ID_QUERY =
            "SELECT " +
                    "    bc.id AS building_company_id, bc.name AS building_company_name, bc.location AS building_company_location, " +
                    "    d.id AS department_id, d.name AS department_name, " +
                    "    c.id AS client_id, c.name AS client_name, c.contact_info AS client_contact_info, c.industry AS client_industry, " +
                    "    e.id AS equipment_id, e.name AS equipment_name, e.equipment_type AS equipment_type " +
                    "FROM building_company bc " +
                    "LEFT JOIN departments d ON bc.id = d.building_company_id " +
                    "LEFT JOIN clients c ON bc.id = c.building_company_id " +
                    "LEFT JOIN equipment e ON bc.id = e.building_company_id " +
                    "WHERE bc.id = ?";

    private static final String SELECT_ALL_QUERY =  "SELECT " +
            "    bc.id AS building_company_id, bc.name AS building_company_name, bc.location AS building_company_location, " +
            "    d.id AS department_id, d.name AS department_name, " +
            "    c.id AS client_id, c.name AS client_name, c.contact_info AS client_contact_info, c.industry AS client_industry, " +
            "    e.id AS equipment_id, e.name AS equipment_name, e.equipment_type AS equipment_type " +
            "FROM building_company bc " +
            "LEFT JOIN departments d ON bc.id = d.building_company_id " +
            "LEFT JOIN clients c ON bc.id = c.building_company_id " +
            "LEFT JOIN equipment e ON bc.id = e.building_company_id";
    private static final String UPDATE_QUERY = "UPDATE building_company SET name = ?, location = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM building_company WHERE id = ?";

    @Override
    public void create(BuildingCompany buildingCompany) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, buildingCompany.getName());
            preparedStatement.setString(2, buildingCompany.getLocation());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating building company failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    buildingCompany.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating building company failed, no ID obtained.");
                }
            }
            LOGGER.info("Building Company created: {}", buildingCompany);
        } catch (SQLException e) {
            LOGGER.error("Unable to create building company: ", e);
            throw new RuntimeException("Unable to create Building Company", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public BuildingCompany findById(Long id) {
        BuildingCompany buildingCompany = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Set<Long> addedDepartments = new HashSet<>();
            Set<Long> addedClients = new HashSet<>();
            Set<Long> addedEquipment = new HashSet<>();

            while (resultSet.next()) {
                if (buildingCompany == null) {
                    buildingCompany = new BuildingCompany();
                    buildingCompany.setId(resultSet.getLong("building_company_id"));
                    buildingCompany.setName(resultSet.getString("building_company_name"));
                    buildingCompany.setLocation(resultSet.getString("building_company_location"));
                    // Initialize collections
                    buildingCompany.setDepartment(new ArrayList<>());
                    buildingCompany.setClients(new ArrayList<>());
                    buildingCompany.setEquipment(new ArrayList<>());
                }

                Department department = DepartmentDAO.mapRow(resultSet);
                if (department != null && addedDepartments.add(department.getId())) {
                    buildingCompany.getDepartment().add(department);
                }

                Client client = ClientDAO.mapRow(resultSet);
                if (client != null && addedClients.add(client.getId())) {
                    buildingCompany.getClients().add(client);
                }

                Equipment equipment = EquipmentDAO.mapRow(resultSet);
                if (equipment != null && addedEquipment.add(equipment.getId())) {
                    buildingCompany.getEquipment().add(equipment);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find building company by id: ", e);
            throw new RuntimeException("Unable to find Building Company by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return buildingCompany;
    }


    @Override
    public List<BuildingCompany> findAll() {
        List<BuildingCompany> buildingCompanies = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                buildingCompanies.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find all building companies: ", e);
            throw new RuntimeException("Unable to find all Building Companies", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return buildingCompanies;
    }

    @Override
    public void update(BuildingCompany buildingCompany) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, buildingCompany.getName());
            preparedStatement.setString(2, buildingCompany.getLocation());
            preparedStatement.setLong(3, buildingCompany.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating building company failed, no rows affected.");
            }
            LOGGER.info("Building Company updated: {}", buildingCompany);
        } catch (SQLException e) {
            LOGGER.error("Unable to update building company: ", e);
            throw new RuntimeException("Unable to update Building Company", e);
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
                throw new SQLException("Deleting building company failed, no rows affected.");
            }
            LOGGER.info("Building Company deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete building company: ", e);
            throw new RuntimeException("Unable to delete Building Company", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private BuildingCompany mapRow(ResultSet resultSet) throws SQLException {
        BuildingCompany buildingCompany = new BuildingCompany();
        buildingCompany.setId(resultSet.getLong("id"));
        buildingCompany.setName(resultSet.getString("name"));
        buildingCompany.setLocation(resultSet.getString("location"));
        return buildingCompany;
    }
}