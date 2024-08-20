package com.example.productservice.inheritanceTablesExample.SingleTable;

import jakarta.persistence.Entity;

@Entity(name="mentors")
public class Mentor extends User {
    private String specialization;
}
