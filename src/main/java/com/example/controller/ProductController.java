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
    @ResponseStatus(HttpStatus.OK)
    public Object addProduct(@RequestParam("stock") Integer stock, @RequestParam("name") String name,@RequestParam("price") Double price){
        Product product=new Product();
        product.setName(name);
        product.setStock(stock);
        product.setPrice(price);
        return productService.addProduct(product);
    }
    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestParam("id") Integer id,@RequestParam(value = "stock",required = false) Integer stock, @RequestParam(value = "name",required = false) String name,@RequestParam(value = "price",required = false) Double price){
        Product product=new Product();
        product.setId(id);
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
    public void deleteProduct(@RequestParam("id") Integer id){
        productService.deleteProduct(id);
    }
}
