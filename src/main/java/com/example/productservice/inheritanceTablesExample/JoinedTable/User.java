package com.example.productservice.inheritanceTablesExample.JoinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "jt-users")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
