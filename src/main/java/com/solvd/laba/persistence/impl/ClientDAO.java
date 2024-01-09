package com.solvd.laba.persistence.impl;


import com.solvd.laba.domain.Client;
import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.persistence.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements ClientRepository {

    private static final Logger LOGGER = LogManager.getLogger(ClientDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO clients (name, contact_info, industry, building_company_id) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM clients WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM clients";
    private static final String UPDATE_QUERY = "UPDATE clients SET name = ?, contact_info = ?, industry = ?, building_company_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM clients WHERE id = ?";

    @Override
    public void create(Client client) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getContactInfo());
            preparedStatement.setString(3, client.getIndustry());
            preparedStatement.setLong(4, client.getBuildingCompany().getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
            LOGGER.info("Client created: {}", client);
        } catch (SQLException e) {
            LOGGER.error("Unable to create client: ", e);
            throw new RuntimeException("Unable to create Client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Client findById(Long id) {
        Client client = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find client by id: ", e);
            throw new RuntimeException("Unable to find Client by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clients.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find all clients: ", e);
            throw new RuntimeException("Unable to find all Clients", e);
        }
        return clients;
    }

    @Override
    public void update(Client client) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getContactInfo());
            preparedStatement.setString(3, client.getIndustry());
            preparedStatement.setLong(4, client.getBuildingCompany().getId());
            preparedStatement.setLong(5, client.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed, no rows affected.");
            }
            LOGGER.info("Client updated: {}", client);
        } catch (SQLException e) {
            LOGGER.error("Unable to update client: ", e);
            throw new RuntimeException("Unable to update Client", e);
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
                throw new SQLException("Deleting client failed, no rows affected.");
            }
            LOGGER.info("Client deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete client: ", e);
            throw new RuntimeException("Unable to delete Client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private Client mapRow(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setName(resultSet.getString("name"));
        client.setContactInfo(resultSet.getString("contact_info"));
        client.setIndustry(resultSet.getString("industry"));
        return client;
    }
}
