package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(Long id);
    public Product addProduct(ProductRequestDto productRequestDto);
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto);
    public Product updateProduct(Long id, ProductRequestDto productRequestDto);
    public void deleteProduct(Long id);
}
