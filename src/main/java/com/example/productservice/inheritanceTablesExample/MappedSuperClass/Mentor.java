package com.example.productservice.inheritanceTablesExample.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="ms-mentors")
public class Mentor extends User{
    private String specialization;
}
