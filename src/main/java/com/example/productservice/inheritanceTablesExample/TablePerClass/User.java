package com.example.productservice.inheritanceTablesExample.TablePerClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "tp-users")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
