package com.example.productservice.controllers;

import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;
    @MockBean
    ProductService productService;
    @Test
    void getProductByIdValidCase() throws InvalidProductIdException {
        Product product = new Product();
        product.setTitle("iphone 14 pro");
        product.setDescription("iphone 14 pro");
        Category category = new Category();
        category.setTitle("Apple");
        category.setId(45L);
        product.setCategory(category);
        product.setPrice(1490000);
        product.setImage("ciphone.com");
        when(productService.getProductById(100L)).thenReturn(product);
        ResponseEntity<ProductResponseDto> responseDto = productController.getProductById(100L);
        assertEquals(product.getTitle(),responseDto.getBody().getTitle());
        assertEquals(product.getDescription(),responseDto.getBody().getDescription());
        assertEquals(product.getPrice(),responseDto.getBody().getPrice());
        assertEquals(product.getImage(),responseDto.getBody().getImage());
        assertEquals(HttpStatus.OK,responseDto.getStatusCode());
    }
    @Test
    void getProductByIdInvalidCase() throws InvalidProductIdException {

        when(productService.getProductById(500L)).thenThrow(new InvalidProductIdException("product not found"));
        assertThrows(InvalidProductIdException.class, () -> productController.getProductById(500L));
    }

    @Test
    void getAllProductsValidCase() {
        List<Product> products = new ArrayList<>();
        for (Product product : products) {
            product.setTitle("iphone 14 pro");
            product.setDescription("iphone 14 pro");
            Category category = new Category();
            category.setTitle("Apple");
            category.setId(45L);
            product.setCategory(category);
            product.setPrice(1490000);
            product.setImage("ciphone.com");
            products.add(product);
        }
        when(productService.getProducts()).thenReturn(products);
        ResponseEntity<List<ProductResponseDto>> responseDto = productController.getAllProducts();
        assertEquals(HttpStatus.OK,responseDto.getStatusCode());

    }
}