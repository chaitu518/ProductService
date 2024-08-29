package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private CategoryResponseDto categoryResponseDto;
    private String description;
    private String image;
}
