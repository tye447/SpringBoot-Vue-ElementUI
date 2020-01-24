package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping(value = "/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Product> productList(){
        return productService.listProduct();
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addProduct(@RequestParam("stock") Integer stock, @RequestParam("name") String name,@RequestParam("price") Double price){
        Product product=new Product();
        product.setName(name);
        product.setStock(stock);
        product.setPrice(price);
        return productService.addProduct(product);
    }

    /*@GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object findById(@RequestParam("id") String id){
           return productService.findById(id);
    }*/

    @GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object findByName(@RequestParam("name") String name){
        return productService.findByName(name);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestParam("id") String id,@RequestParam(value = "stock",required = false) Integer stock, @RequestParam(value = "name",required = false) String name,@RequestParam(value = "price",required = false) Double price){
        Product product=new Product();
        product.setId(Integer.parseInt(id));
        if(name!=null) {
            product.setName(name);
        }
        if(stock!=null) {
            product.setStock(stock);
        }
        if(price!=null) {
            product.setPrice(price);
        }
        return productService.updateProduct(id,product);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam("id") String id){
        productService.deleteProduct(id);
    }
}
