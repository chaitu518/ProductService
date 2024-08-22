package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
//@Primary
@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService{
    RestTemplate restTemplate;
    FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }
    @Override
    public List<Product> getProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos=restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        if(fakeStoreProductDtos==null) {
            return null;
        }
        List<Product> products=new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(fakeStoreProductDto==null) {
            return null;
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    private ProductDto convertProductRequestDtoToProductDto(ProductRequestDto productRequestDto){
        ProductDto productDto = new ProductDto();
        productDto.setTitle(productRequestDto.getTitle());
        productDto.setDescription(productRequestDto.getDescription());
        productDto.setPrice(productRequestDto.getPrice());
        productDto.setImage(productRequestDto.getImage());
        productDto.setCategory(productRequestDto.getCategory().getTitle());
        return productDto;
    }
    @Override
    public Product addProduct(ProductRequestDto productRequestDto) {
        ProductDto productDto = convertProductRequestDtoToProductDto(productRequestDto);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", productDto, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto) {
        ProductDto productDto = convertProductRequestDtoToProductDto(productRequestDto);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =(restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor));
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long id,ProductRequestDto productRequestDto) {
        ProductDto productDto = convertProductRequestDtoToProductDto(productRequestDto);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(response);
    }

    @Override
    public void deleteProduct(Long id) {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        convertFakeStoreProductDtoToProduct(restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, requestCallback, responseExtractor));
    }
}
