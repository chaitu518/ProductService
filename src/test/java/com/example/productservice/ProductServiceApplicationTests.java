package com.example.productservice;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.repositories.projections.ProductIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;
	@Test
	void contextLoads() {
	}
	@Test
	public void queryTestForHQL() {
		List<Product> products = productRepository.someRandomHQL();
		for (Product product : products) {
			System.out.println(product);
		}
	}
	@Test
	public void queryTestForSQL() {
		ProductIdAndTitle product = productRepository.someRandomSQL(1L);
		System.out.println(product.getId()+" "+product.getTitle());
	}



}
