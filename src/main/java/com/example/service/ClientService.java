package com.example.service;

import com.example.model.Client;
import com.example.common.Result;
import com.example.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.example.util.Util.getNullPropertyNames;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public List<Client> listClient(){
        return clientRepository.findAll();
    }
    public Client addClient(Client client){
        return clientRepository.save(client);
    }
    public void deleteClient(String id){
        clientRepository.deleteById(id);
    }
    public Client updateClient(String id,Client client){
        Client currentInstance=clientRepository.findById(id).orElse(null);
        String[] nullPropertyNames = getNullPropertyNames(client);
        BeanUtils.copyProperties(client, currentInstance, nullPropertyNames);
        return clientRepository.save(currentInstance);
    }
    public Client findById(String id){
        Client client = clientRepository.findById(id).orElse(null);
        return client;
    }
    public Client findByName(String name){
        Client client=clientRepository.findByName(name);
        return client;
    }
}
