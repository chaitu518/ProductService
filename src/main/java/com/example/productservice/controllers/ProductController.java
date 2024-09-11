package com.example.productservice.controllers;

import com.example.productservice.dtos.CategoryResponseDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
        Product product= productService.getProductById(id);
            ProductResponseDto responseDto = new ProductResponseDto();
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setId(product.getCategory().getId());
            categoryResponseDto.setTitle(product.getCategory().getTitle());
            responseDto.setCategoryResponseDto(categoryResponseDto);
            responseDto.setTitle(product.getTitle());
            responseDto.setPrice(product.getPrice());
            responseDto.setDescription(product.getDescription());
            responseDto.setId(product.getId());
            responseDto.setImage(product.getImage());
            return new ResponseEntity<ProductResponseDto>(responseDto, HttpStatus.OK);
            //return new ResponseEntity<>(product,HttpStatus.CREATED);

    }
    //@GetMapping("/all/{token}")
    @GetMapping("/")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize,@RequestParam("sortDir") String sortDir) throws InvalidProductIdException {
        Page<Product> productPage = productService.getProducts(pageNumber,pageSize,sortDir);
        return new ResponseEntity<>(productPage, HttpStatus.OK);

    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(HttpServletRequest request) throws InvalidProductIdException {
        //getting token from header
        String authorizationHeader = request.getHeader("Authorization");

        // Check if the token is provided and starts with "Bearer "
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Extract the token (remove "Bearer " prefix)
        String token = authorizationHeader.substring(7);

        //return new ArrayList<Product>();

        List<Product> products = productService.getProducts(token);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setId(product.getCategory().getId());
            categoryResponseDto.setTitle(product.getCategory().getTitle());
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getId());
            productResponseDto.setTitle(product.getTitle());
            productResponseDto.setPrice(product.getPrice());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setImage(product.getImage());
            productResponseDto.setCategoryResponseDto(categoryResponseDto);
            productResponseDtos.add(productResponseDto);


        }
        return new ResponseEntity<List<ProductResponseDto>>(productResponseDtos,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product= productService.addProduct(productRequestDto);
        if(product != null){
            ProductResponseDto responseDto = new ProductResponseDto();
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setId(product.getCategory().getId());
            categoryResponseDto.setTitle(product.getCategory().getTitle());
            responseDto.setCategoryResponseDto(categoryResponseDto);
            responseDto.setTitle(product.getTitle());
            responseDto.setPrice(product.getPrice());
            responseDto.setDescription(product.getDescription());
            responseDto.setId(product.getId());
            responseDto.setImage(product.getImage());
            return new ResponseEntity<ProductResponseDto>(responseDto, HttpStatus.OK);
            //return new ResponseEntity<>(product,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto){
        Product product = productService.replaceProduct(id,productRequestDto);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            ProductResponseDto responseDto = new ProductResponseDto();
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setId(product.getCategory().getId());
            categoryResponseDto.setTitle(product.getCategory().getTitle());
            responseDto.setCategoryResponseDto(categoryResponseDto);
            responseDto.setTitle(product.getTitle());
            responseDto.setPrice(product.getPrice());
            responseDto.setDescription(product.getDescription());
            responseDto.setId(product.getId());
            responseDto.setImage(product.getImage());
            return new ResponseEntity<ProductResponseDto>(responseDto, HttpStatus.OK);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") Long id,@RequestBody ProductRequestDto productRequestDto){

        Product product = productService.updateProduct(id,productRequestDto);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            ProductResponseDto responseDto = new ProductResponseDto();
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setId(product.getCategory().getId());
            categoryResponseDto.setTitle(product.getCategory().getTitle());
            responseDto.setCategoryResponseDto(categoryResponseDto);
            responseDto.setTitle(product.getTitle());
            responseDto.setPrice(product.getPrice());
            responseDto.setDescription(product.getDescription());
            responseDto.setId(product.getId());
            responseDto.setImage(product.getImage());
            return new ResponseEntity<ProductResponseDto>(responseDto, HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
