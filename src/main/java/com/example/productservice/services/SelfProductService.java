package com.example.productservice.services;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfproductservice")
public class SelfProductService implements ProductService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new RuntimeException("Product not found");
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
}
