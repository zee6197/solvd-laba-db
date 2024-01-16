package com.solvd.laba.persistence.impl;


import com.solvd.laba.domain.Equipment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.EquipmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO implements EquipmentRepository {

    private static final Logger LOGGER = LogManager.getLogger(EquipmentDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO equipment (name, equipment_type, building_company_id) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM equipment WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM equipment";
    private static final String UPDATE_QUERY = "UPDATE equipment SET name = ?, equipment_type = ?, building_company_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM equipment WHERE id = ?";

    @Override
    public void create(Equipment equipment, Long companyID) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setString(2, equipment.getEquipmentType());
            preparedStatement.setLong(3, companyID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating equipment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    equipment.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating equipment failed, no ID obtained.");
                }
            }
            LOGGER.info("Equipment created: {}", equipment);
        } catch (SQLException e) {
            LOGGER.error("Unable to create equipment: ", e);
            throw new RuntimeException("Unable to create Equipment", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Equipment findById(Long id) {
        Equipment equipment = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                equipment = mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find equipment by id: ", e);
            throw new RuntimeException("Unable to find Equipment by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return equipment;
    }

    @Override
    public List<Equipment> findAll() {
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                equipmentList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find all equipment: ", e);
            throw new RuntimeException("Unable to find all Equipment", e);
        }
        return equipmentList;
    }

    @Override
    public void update(Equipment equipment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setString(2, equipment.getEquipmentType());
            preparedStatement.setLong(4, equipment.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating equipment failed, no rows affected.");
            }
            LOGGER.info("Equipment updated: {}", equipment);
        } catch (SQLException e) {
            LOGGER.error("Unable to update equipment: ", e);
            throw new RuntimeException("Unable to update Equipment", e);
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
                throw new SQLException("Deleting equipment failed, no rows affected.");
            }
            LOGGER.info("Equipment deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete equipment: ", e);
            throw new RuntimeException("Unable to delete Equipment", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static Equipment mapRow(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setId(resultSet.getLong("id"));
        equipment.setName(resultSet.getString("name"));
        equipment.setEquipmentType(resultSet.getString("equipment_type"));
        return equipment;
    }
}