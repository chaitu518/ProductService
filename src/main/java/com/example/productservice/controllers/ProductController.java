package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @GetMapping("")
    public List<Product> getAllProducts(){
        //return new ArrayList<Product>();
        return productService.getProducts();
    }
    @PostMapping
    public Product addProduct(Product product){
        return new Product();
    }
    @PutMapping("/{id}")
    public Product replaceProduct(Product product){
        return new Product();
    }
    @PatchMapping("/{id}")
    public Product updateProduct(Product product){
        return new Product();
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){

    }
}
