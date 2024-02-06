package com.solvd.laba.unit;

import com.solvd.laba.domain.Client;
import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.service.impl.ClientServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() {

        Client client = new Client();
        client.setName("testName");
        client.setContactInfo("testContactInfo");
        client.setIndustry("testIndustry");
        Long companyID = 1L;
        doNothing().when(clientRepository).create(client, companyID);
        clientService.create(client, companyID);
        verify(clientRepository, times(1)).create(client, companyID);
    }

    @Test
    public void testRetrieveById() {

        Long clientId = 1L;
        // Precondition
        Client expectedClient = new Client();
        expectedClient.setName("testName");
        expectedClient.setContactInfo("testContactInfo");
        expectedClient.setIndustry("testIndustry");
        // Test
        when(clientRepository.findById(clientId)).thenReturn(expectedClient);
        Client actualClient = clientService.retrieveById(clientId);
        Assert.assertEquals(actualClient, expectedClient);
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testRetrieveAll() {

        List<Client> expectedClients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(expectedClients);
        List<Client> actualClients = clientService.retrieveAll();
        Assert.assertEquals(actualClients, expectedClients);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client();
        doNothing().when(clientRepository).update(client);
        clientService.update(client);
        verify(clientRepository, times(1)).update(client);
    }

    @Test
    public void testDeleteClient() {

        Long clientId = 1L;
        doNothing().when(clientRepository).delete(clientId);
        clientService.delete(clientId);
        verify(clientRepository, times(1)).delete(clientId);
    }
}
