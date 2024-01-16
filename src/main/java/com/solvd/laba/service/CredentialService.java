package com.solvd.laba.service;

import com.solvd.laba.domain.Credential;
import java.util.List;


public interface CredentialService {

    void create(Credential credential);
    Credential retrieveById(Long id);
    List<Credential> retrieveAll();
    void update(Credential credential);
    void delete(Long id);
}
