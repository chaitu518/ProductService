package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(Long id);
    public Product addProduct(ProductDto productDto);
    public Product replaceProduct(Long id, ProductDto productDto);
    public Product updateProduct(Long id, ProductDto productDto);
    public Product deleteProduct(Long id);
}
