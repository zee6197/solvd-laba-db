package com.solvd.laba.service;

import com.solvd.laba.domain.Client;
import java.util.List;

public interface ClientService {

    void create(Client client);
    Client retrieveById(Long id);
    List<Client> retrieveAll();
    void update(Client client);
    void delete(Long id);
}