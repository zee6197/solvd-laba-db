package com.solvd.laba.persistence.mybatis;


import com.solvd.laba.domain.Credential;
import com.solvd.laba.persistence.CredentialRepository;
import com.solvd.laba.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CredentialMyBatisDAO implements CredentialRepository {

    @Override
    public long create(Credential credential) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            CredentialRepository mapper = session.getMapper(CredentialRepository.class);
            mapper.create(credential);
            session.commit();
            return credential.getId();
        }
    }

    @Override
    public Credential findById(Long id) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            CredentialRepository mapper = session.getMapper(CredentialRepository.class);
            return mapper.findById(id);
        }
    }

    @Override
    public List<Credential> findAll() {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            CredentialRepository mapper = session.getMapper(CredentialRepository.class);
            return mapper.findAll();
        }
    }

    @Override
    public void update(Credential credential) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            CredentialRepository mapper = session.getMapper(CredentialRepository.class);
            mapper.update(credential);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            CredentialRepository mapper = session.getMapper(CredentialRepository.class);
            mapper.delete(id);
        }
    }
}
