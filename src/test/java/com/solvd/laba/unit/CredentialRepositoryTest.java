package com.solvd.laba.unit;

import com.solvd.laba.domain.Credential;
import com.solvd.laba.persistence.CredentialRepository;
import com.solvd.laba.persistence.impl.CredentialDAO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CredentialRepositoryTest {

    private CredentialRepository credentialRepository;

    @BeforeClass
    public void setUp() {

        // Credential repository instance
        credentialRepository = new CredentialDAO();
    }


    @Test
    public void testCreateCredential() {

        Credential credential = new Credential();
        credential.setLogin("John");
        credential.setPassword("Smith");
        long credentialId = credentialRepository.create(credential);
        assertTrue(credentialId > 0, "Credential ID should be greater than zero.");
    }

    @Test
    public void testFindCredentialById() {

        Long credentialId = 1L;
        Credential foundCredential = credentialRepository.findById(credentialId);
        assertNotNull(foundCredential, "The retrieved credential should not be null.");
        assertEquals(foundCredential.getId(), credentialId, "The retrieved credential ID should match the created one.");
    }


    @Test
    public void testFindAllCredentials() {

        List<Credential> credentials = credentialRepository.findAll();
        assertNotNull(credentials, "The list of credentials should not be null.");
        assertFalse(credentials.isEmpty(), "The list of credentials should not be empty.");
    }


    @Test
    public void testUpdateCredential() {

        Long credentialId = 1L;
        Credential credentials = credentialRepository.findById(credentialId);
        credentials.setId(credentialId);
        credentials.setPassword("NewPassword");
        credentialRepository.update(credentials);

        Credential updatedCredential = credentialRepository.findById(credentialId);
        assertNotNull(credentials, "The list of credentials should not be null.");
        assertEquals(updatedCredential.getPassword(), "NewPassword", "The password of the credential should be updated.");
    }


    @Test
    public void testDeleteCredential() {
        // Precondition
        Credential credential = new Credential();
        credential.setLogin("John");
        credential.setPassword("Smith");
        long credentialId = credentialRepository.create(credential);

        // Test
        credentialRepository.delete(credentialId);
        Credential deletedCredential = credentialRepository.findById(credentialId);
        assertNull(deletedCredential, "The credential should be null after deletion.");
    }
}
