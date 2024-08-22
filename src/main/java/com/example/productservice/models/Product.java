package com.example.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    private String title;
    private double price;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    private Category category;
    private String description;
    private String image;
    //private int quantity;
}
