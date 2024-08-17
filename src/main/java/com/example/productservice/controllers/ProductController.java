package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){
        Product product1 = productService.addProduct(productDto);
        if(product1 != null){
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        Product product = productService.replaceProduct(id,productDto);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto){

        Product product = productService.updateProduct(id,productDto);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){
        Product product = productService.deleteProduct(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
