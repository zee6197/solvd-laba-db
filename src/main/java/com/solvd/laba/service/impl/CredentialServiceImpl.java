package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Credential;
import com.solvd.laba.persistence.CredentialRepository;
import com.solvd.laba.persistence.impl.CredentialDAO;
import com.solvd.laba.persistence.mybatis.CredentialMyBatisDAO;
import com.solvd.laba.service.CredentialService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CredentialServiceImpl implements CredentialService {
    private static final Logger LOGGER = LogManager.getLogger(CredentialServiceImpl.class);

    private final CredentialRepository credentialRepository;

    public CredentialServiceImpl() {

        this.credentialRepository = new CredentialMyBatisDAO();

    }

    @Override
    public void create(Credential credential) {
        LOGGER.info("Creating credential");
        credentialRepository.create(credential);
    }

    @Override
    public Credential retrieveById(Long id) {
        LOGGER.info("Retrieving credential by id");
        return credentialRepository.findById(id);
    }

    @Override
    public List<Credential> retrieveAll() {
        LOGGER.info("Retrieving all credentials");
        return credentialRepository.findAll();
    }

    @Override
    public void update(Credential credential) {
        LOGGER.info("Updating credential");
        credentialRepository.update(credential);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting credential with ID: {}", id);
        credentialRepository.delete(id);
    }
}
