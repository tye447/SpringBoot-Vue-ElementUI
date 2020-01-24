package com.example.controller;

import com.example.model.Client;
import com.example.model.Commande;
import com.example.service.ClientService;
import com.example.service.CommandeService;
import com.example.service.EmployeeService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commande")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private ClientService clientService;
   @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductService productService;
    @GetMapping(value = "/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Commande> commandeList(){
        return commandeService.listCommande();
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addCommande(@RequestParam("client_name") String client_name,
                              @RequestParam("employee_name") String employee_name,
                              @RequestParam("product_name") String product_name,
                              @RequestParam("quantity") Integer quantity){
        Commande commande=new Commande();
        if(client_name!=null) {
            try {
                Client client = clientService.findByName(client_name);
                commande.setClient(client);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        if(employee_name!=null) {
            commande.setEmployee(employeeService.findByName(employee_name));
        }
        if(product_name!=null) {
            commande.setProduct(productService.findByName(product_name));
        }
        if(quantity!=null) {
            commande.setQuantity(quantity);
        }
        return commandeService.addCommande(commande);
    }

    @GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCommande(String method,@RequestParam("id") String id){
           return commandeService.getCommande(id);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Commande updateCommande(@RequestParam("id") String id,
                                   @RequestParam(value = "client_name",required = false) String client_name,
                                   @RequestParam(value = "employee_name",required = false) String employee_name,
                                   @RequestParam(value = "product_name",required = false) String product_name,
                                   @RequestParam(value = "quantity",required = false) Integer quantity) throws NullPointerException{
        Commande commande=new Commande();
        commande.setId(Integer.parseInt(id));
        if(client_name!=null) {
            commande.setClient(clientService.findByName(client_name));
        }
        if(employee_name!=null) {
            commande.setEmployee(employeeService.findByName(employee_name));
        }
        if(product_name!=null) {
            commande.setProduct(productService.findByName(product_name));
        }
        if(quantity!=null) {
            commande.setQuantity(quantity);
        }
        return commandeService.updateCommande(id,commande);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteCommande(@RequestParam("id") String id){
        commandeService.deleteCommande(id);
    }
}
