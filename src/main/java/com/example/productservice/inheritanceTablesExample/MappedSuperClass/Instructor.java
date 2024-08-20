package com.example.productservice.inheritanceTablesExample.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "ms-instructors")
public class Instructor extends User {
    private int noOfSessions;
}
