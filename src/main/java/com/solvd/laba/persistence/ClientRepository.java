package com.solvd.laba.persistence;

import com.solvd.laba.domain.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientRepository {

    void create(@Param("client") Client client, @Param("companyID") Long companyID);
    Client findById(Long id);

    List<Client> findAll();

    void update(Client client);

    void delete(Long id);
}
