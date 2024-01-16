package com.solvd.laba.persistence;

import com.solvd.laba.domain.Credential;

import java.util.List;

public interface CredentialRepository {

    void create(Credential credential);
    Credential findById(Long id);
    List<Credential> findAll();
    void update(Credential credential);
    void delete(Long id);
}