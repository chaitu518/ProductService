package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(Long id);
    public Product addProduct(Product product);
    public Product replaceProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Long id);
}
