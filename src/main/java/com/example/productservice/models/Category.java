package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModel{
    private String title;
    //@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    //private List<Product> products;
}
