package com.example.controller;

import com.example.model.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("client")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping(value = "/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Client> clientList(){
        return clientService.listClient();
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addClient(@RequestParam("name") String name,@RequestParam("age") Integer age){
        Client client=new Client();
        client.setName(name);
        client.setAge(age);
        return clientService.addClient(client);
    }

    /*@GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Client findById(@RequestParam("id") String id){
           return clientService.findById(id);
    }*/

    @GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Client findByName(@RequestParam("name") String name){
        Client client= clientService.findByName(name);
        return client;
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@RequestParam("id") String id, @RequestParam(value = "name",required = false) String name,@RequestParam(value = "age",required = false) Integer age) throws NullPointerException{
        Client client=new Client();
        client.setId(Integer.parseInt(id));
        if(name!=null) {
            client.setName(name);
        }
        if(age!=null) {
            client.setAge(age);
        }
        return clientService.updateClient(id,client);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@RequestParam("id") String id){
        clientService.deleteClient(id);
    }
}
