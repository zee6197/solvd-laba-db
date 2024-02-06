package com.solvd.laba.unit;

import com.solvd.laba.domain.Client;
import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.service.impl.ClientServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClientServiceImplTest {
    @Mock
    private ClientRepository mockClientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(mockClientRepository);
    }

    @Test
    public void testCreateClient() {

        Client client = new Client();
        client.setName("testName");
        client.setContactInfo("testContactInfo");
        client.setIndustry("testIndustry");
        Long companyID = 1L;
        doNothing().when(mockClientRepository).create(client, companyID);
        clientService.create(client, companyID);
        verify(mockClientRepository, times(1)).create(client, companyID);
    }

    @Test
    public void testRetrieveById() {
        Long clientId = 1L;
        Client expectedClient = new Client();
        expectedClient.setName("testName");
        expectedClient.setContactInfo("testContactInfo");
        expectedClient.setIndustry("testIndustry");
        when(mockClientRepository.findById(clientId)).thenReturn(expectedClient);
        Client actualClient = clientService.retrieveById(clientId);
        Assert.assertEquals(actualClient, expectedClient);
        verify(mockClientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testRetrieveAll() {
        List<Client> expectedClients = Arrays.asList(new Client(), new Client());
        when(mockClientRepository.findAll()).thenReturn(expectedClients);
        List<Client> actualClients = clientService.retrieveAll();
        Assert.assertEquals(actualClients, expectedClients);
        verify(mockClientRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client();
        doNothing().when(mockClientRepository).update(client);
        clientService.update(client);
        verify(mockClientRepository, times(1)).update(client);
    }

    @Test
    public void testDeleteClient() {

        Long clientId = 1L;
        doNothing().when(mockClientRepository).delete(clientId);
        clientService.delete(clientId);
        verify(mockClientRepository, times(1)).delete(clientId);
    }
}