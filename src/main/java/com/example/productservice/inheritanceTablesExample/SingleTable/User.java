package com.example.productservice.inheritanceTablesExample.SingleTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "single-users")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
