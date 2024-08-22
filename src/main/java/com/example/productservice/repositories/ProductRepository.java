package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.projections.ProductIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    @Override
    void deleteById(Long id);

    //HQL query
    @Query(value = "select p from products p where p.title like '%iphone%' ")
    List<Product> someRandomHQL();

    @Query(value = "select p.id,p.title from products p where p.id=:id ",nativeQuery = true)
    ProductIdAndTitle someRandomSQL(@Param("id") Long id);
}