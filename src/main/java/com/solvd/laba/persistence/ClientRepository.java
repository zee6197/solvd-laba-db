package com.solvd.laba.persistence;

import com.solvd.laba.domain.Client;

import java.util.List;

public interface ClientRepository {

    void create(Client client, Long companyID);
    Client findById(Long id);
    List<Client> findAll();
    void update(Client client);
    void delete(Long id);
}