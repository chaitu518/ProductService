package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    ProductController(@Qualifier("selfproductservice") ProductService productService) {
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
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product1 = productService.addProduct(productRequestDto);
        if(product1 != null){
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto){
        Product product = productService.replaceProduct(id,productRequestDto);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody ProductRequestDto productRequestDto){

        Product product = productService.updateProduct(id,productRequestDto);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
