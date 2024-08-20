package com.example.productservice.inheritanceTablesExample.SingleTable;

import jakarta.persistence.Entity;

@Entity
public class Instructor extends User {
    private int noOfSessions;
}
