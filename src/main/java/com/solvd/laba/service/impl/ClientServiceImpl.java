package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Client;

import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.persistence.impl.ClientDAO;
import com.solvd.laba.persistence.mybatis.ClientMyBatisDAO;
import com.solvd.laba.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public ClientServiceImpl() {


        this.clientRepository = new ClientDAO();
    }

    @Override
    public void create(Client client, Long companyID) {
        LOGGER.info("Creating client");
        clientRepository.create(client, companyID);
    }

    @Override
    public Client retrieveById(Long id) {
        LOGGER.info("Retrieving client by id");
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> retrieveAll() {
        LOGGER.info("Retrieving all clients");
        return clientRepository.findAll();
    }

    @Override
    public void update(Client client) {
        LOGGER.info("Updating client");
        clientRepository.update(client);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting client with ID: {}", id);
        clientRepository.delete(id);
    }
}