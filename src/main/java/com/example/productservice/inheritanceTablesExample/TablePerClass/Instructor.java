package com.example.productservice.inheritanceTablesExample.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tp-instructors")
public class Instructor extends User {
    private int noOfSessions;
}
