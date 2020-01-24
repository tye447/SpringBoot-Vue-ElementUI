package com.example.service;

import com.example.model.Client;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.util.Util.getNullPropertyNames;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> listProduct(){
        return productRepository.findAll();
    }
    public Product addProduct(Product Product){
        return productRepository.save(Product);
    }
    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }
    public Product updateProduct(String id,Product Product){
        Product currentInstance= productRepository.findById(id).orElse(null);
        String[] nullPropertyNames = getNullPropertyNames(Product);
        BeanUtils.copyProperties(Product, currentInstance, nullPropertyNames);
        return productRepository.save(currentInstance);
    }
    public Product findById(String id){
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
    public Product findByName(String name){
        return productRepository.findByName(name);
    }
}
