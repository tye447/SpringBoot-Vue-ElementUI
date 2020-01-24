package com.example.service;

import com.example.model.Commande;
import com.example.repository.CommandeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.util.Util.getNullPropertyNames;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    public List<Commande> listCommande(){
        return commandeRepository.findAll();
    }
    public Commande getCommande(String id){
        Commande commande = commandeRepository.findById(id).orElse(null);
        return commande;
    }
    public Commande addCommande(Commande commande){
        return commandeRepository.save(commande);
    }
    public void deleteCommande(String id){
        commandeRepository.deleteById(id);
    }
    public Commande updateCommande(String id,Commande commande){
        Commande currentInstance=commandeRepository.findById(id).orElse(null);
        String[] nullPropertyNames = getNullPropertyNames(commande);
        BeanUtils.copyProperties(commande, currentInstance, nullPropertyNames);
        return commandeRepository.save(currentInstance);
    }
}
