package com.example.productservice.repositories;

import com.example.productservice.dtos.ProductDocument;
import com.example.productservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElasticSearchProductRepository extends ElasticsearchRepository<ProductDocument,Long> {
    List<ProductDocument> findByTitleStartingWith(String title);
}
