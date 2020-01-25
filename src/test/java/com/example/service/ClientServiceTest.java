package com.example.service;

import com.example.model.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @Transactional
    @Test
    public void getListClientTest(){
        List<Client> clientList=clientService.listClient();
    }

    @Transactional
    @Test
    public void getClientEroorTest(){
        Client client=clientService.findById(20);
        assertNull(client);
    }

}
