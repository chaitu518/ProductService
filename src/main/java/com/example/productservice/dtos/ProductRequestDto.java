package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String title;
    private double price;
    private Category category;
    private String description;
    private String image;

}
