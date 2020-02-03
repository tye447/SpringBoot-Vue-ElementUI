package com.example.repository;

import com.example.model.Commande;
import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    Commande findByProduct(Product product);
}

