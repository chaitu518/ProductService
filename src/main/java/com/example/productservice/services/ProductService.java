package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Page<Product> getProducts(int pageNumber, int pageSize,String sortDir) throws InvalidProductIdException;
    public List<Product> getProducts(String token) throws InvalidProductIdException;
    public Product getProductById(Long id) throws InvalidProductIdException;
    public Product addProduct(ProductRequestDto productRequestDto);
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto);
    public Product updateProduct(Long id, ProductRequestDto productRequestDto);
    public void deleteProduct(Long id);
    public List<ProductDto> searchProductByprefix(String title);
}
