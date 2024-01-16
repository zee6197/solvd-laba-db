package com.solvd.laba.persistence.mybatis;

import com.solvd.laba.domain.Client;
import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientMyBatisDAO implements ClientRepository {

    @Override
    public void create(Client client) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            ClientRepository mapper = session.getMapper(ClientRepository.class);
            mapper.create(client);
        }
    }

    @Override
    public Client findById(Long id) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            ClientRepository mapper = session.getMapper(ClientRepository.class);
            return mapper.findById(id);
        }
    }

    @Override
    public List<Client> findAll() {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            ClientRepository mapper = session.getMapper(ClientRepository.class);
            return mapper.findAll();
        }
    }

    @Override
    public void update(Client client) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            ClientRepository mapper = session.getMapper(ClientRepository.class);
            mapper.update(client);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            ClientRepository mapper = session.getMapper(ClientRepository.class);
            mapper.delete(id);
        }
    }
}
