package com.example.productservice.inheritanceTablesExample.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tp-mentors")
public class Mentor extends User {
    private String specialization;
}
