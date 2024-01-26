package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Credential;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.CredentialRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialDAO implements CredentialRepository {


    private static final Logger LOGGER = LogManager.getLogger(CredentialDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO credentials (login, password) VALUES (?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, login, password FROM credentials WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT id, login, password FROM credentials";
    private static final String UPDATE_QUERY = "UPDATE credentials SET login = ?, password = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM credentials WHERE id = ?";

    @Override
    public long create(Credential credential) {
        Connection connection = CONNECTION_POOL.getConnection();
        long generatedId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, credential.getLogin());
            preparedStatement.setString(2, credential.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating credential failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getLong(1);
                    credential.setId(generatedId);
                    LOGGER.info("Credential created with ID: {}", generatedId);
                } else {
                    throw new SQLException("Creating credential failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to create credential: ", e);
            throw new RuntimeException("Unable to create Credential", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return generatedId;
    }


    @Override
    public Credential findById(Long id) {
        Credential credential = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                credential = mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find credential by id: ", e);
            throw new RuntimeException("Unable to find Credential by ID", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return credential;
    }

    @Override
    public List<Credential> findAll() {
        List<Credential> credentials = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                credentials.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to find all credentials: ", e);
            throw new RuntimeException("Unable to find all Credentials", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return credentials;
    }

    @Override
    public void update(Credential credential) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, credential.getLogin());
            preparedStatement.setString(2, credential.getPassword());
            preparedStatement.setLong(3, credential.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating credential failed, no rows affected.");
            }
            LOGGER.info("Credential updated: {}", credential);
        } catch (SQLException e) {
            LOGGER.error("Unable to update credential: ", e);
            throw new RuntimeException("Unable to update Credential", e);
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
                throw new SQLException("Deleting credential failed, no rows affected.");
            }
            LOGGER.info("Credential deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Unable to delete credential: ", e);
            throw new RuntimeException("Unable to delete Credential", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static Credential mapRow(ResultSet resultSet) throws SQLException {
        Credential credential = new Credential();
        credential.setId(resultSet.getLong("id"));
        credential.setLogin(resultSet.getString("login"));
        credential.setPassword(resultSet.getString("password"));
        return credential;
    }
}
