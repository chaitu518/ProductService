package com.example.productservice.inheritanceTablesExample.JoinedTable;

import jakarta.persistence.Entity;

@Entity(name="jt-mentors")
public class Mentor extends User {
    private String specialization;
}
