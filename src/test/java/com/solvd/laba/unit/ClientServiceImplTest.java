package com.solvd.laba.unit;

import com.solvd.laba.domain.Client;
import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.service.impl.ClientServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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