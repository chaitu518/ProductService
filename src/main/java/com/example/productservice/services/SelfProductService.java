package com.example.productservice.services;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.example.productservice.commons.AuthenticationCommons;
import com.example.productservice.dtos.ProductDocument;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.dtos.UserDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ElasticSearchProductRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service("selfproductservice")
public class SelfProductService implements ProductService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    AuthenticationCommons authenticationCommons;
    ElasticSearchProductRepository elasticSearchProductRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository,AuthenticationCommons commons,ElasticSearchProductRepository elasticSearchProductRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.authenticationCommons = commons;
        this.elasticSearchProductRepository = elasticSearchProductRepository;

    }

    @Override
    public Page<Product> getProducts(int pageNumber,int pagesize,String sortDir) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber,pagesize,
                sortDir.equalsIgnoreCase("desc")?Sort.by("price").descending():Sort.by("price").ascending()));
        return productPage;
    }

    @Override
    public List<Product> getProducts(String token) {
        UserDto userDto = authenticationCommons.validateToken(token);
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new InvalidProductIdException("Product not found");
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDto) {
        if(productRequestDto==null){
            throw new RuntimeException("Product Request is empty");
        }
        if(productRequestDto.getId()!=null){
            throw new RuntimeException("Product with id "+productRequestDto.getId()+" already exists");
        }
        if(productRequestDto.getCategory()==null){
            throw new RuntimeException("Category is empty");
        }
        Product product = new Product();
        if(productRequestDto.getCategory().getId()!=null){
            Category category = categoryRepository.findById(productRequestDto.getCategory().getId()).get();
            product.setCategory(category);
        }
        else{
            product.setCategory(productRequestDto.getCategory());
        }

        if(productRequestDto.getDescription()!=null){
            product.setDescription(productRequestDto.getDescription());
        }
        if(productRequestDto.getTitle()!=null){
            product.setTitle(productRequestDto.getTitle());
        }
        if(productRequestDto.getPrice()!=0) {
            product.setPrice(productRequestDto.getPrice());
        }
        if(productRequestDto.getImage()!=null){
            product.setImage(productRequestDto.getImage());
        }
        Product savedProduct = productRepository.save(product);

        elasticSearchProductRepository.save(convertProductToProductDocument(product));


        //System.out.println(elasticSearchProductRepository.findById(savedProduct.getId()));
        return savedProduct;
    }

    @Override
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto) {
        Product product =  updatedProductMethod(id, productRequestDto);

        if(productRequestDto.getCategory()==null){

            throw new RuntimeException("Category is empty");
        }
        Category category=null;
        if(productRequestDto.getCategory().getId()==null){
            category=categoryRepository.save(productRequestDto.getCategory());
        }
        else{
            Optional<Category> optionalCategory = categoryRepository.findById(productRequestDto.getCategory().getId());
            if(optionalCategory.isEmpty()){
                throw new RuntimeException("Category with id "+productRequestDto.getCategory().getId()+" does not exist");
            }
            category = optionalCategory.get();
        }

        product.setCategory(category);
        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {

        return updatedProductMethod(id, productRequestDto);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    //To update/replace product logic
    private Product updatedProductMethod(Long id,ProductRequestDto productRequestDto) {
        Optional<Product> productOptional= productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new RuntimeException("Product with id "+id+" does not exist");
        }

        Product product = productOptional.get();
        if(productRequestDto.getDescription()!=null){
            product.setDescription(productRequestDto.getDescription());
        }
        if(productRequestDto.getTitle()!=null){
            product.setTitle(productRequestDto.getTitle());
        }
        if(productRequestDto.getPrice()!=0) {
            product.setPrice(productRequestDto.getPrice());
        }
        if(productRequestDto.getImage()!=null){
            product.setImage(productRequestDto.getImage());
        }
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }
    public List<ProductDto> searchProductByprefix(String title){

       List<ProductDocument> productDocuments = elasticSearchProductRepository.findByTitleStartingWith(title);
       if(productDocuments.size()==0){
           return null;
       }
       List<ProductDto> productDtos = new ArrayList<>();
       for(ProductDocument productDocument : productDocuments){
           productDtos.add(convertProductDocumentToProductDto(productDocument));
       }
       return productDtos;
    }
    private ProductDocument convertProductToProductDocument(Product product) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(String.valueOf(product.getId()));
        productDocument.setTitle(product.getTitle());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        productDocument.setImage(product.getImage());
        productDocument.setCategory(product.getCategory().getTitle());
        return productDocument;
    }
    private ProductDto convertProductDocumentToProductDto(ProductDocument productDocument) {
        ProductDto productDto = new ProductDto();
        productDto.setCategory(productDocument.getCategory());
        productDto.setDescription(productDocument.getDescription());
        productDto.setTitle(productDocument.getTitle());
        productDto.setPrice(productDocument.getPrice());
        productDto.setImage(productDocument.getImage());
        return productDto;
    }
}
