package com.example.productservice.inheritanceTablesExample.JoinedTable;

import jakarta.persistence.Entity;

@Entity(name = "jt-instructors")
public class Instructor extends User {
    private int noOfSessions;
}
