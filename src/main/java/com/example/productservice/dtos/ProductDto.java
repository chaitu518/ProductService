package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
