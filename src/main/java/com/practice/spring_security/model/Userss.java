package com.practice.spring_security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Userss{

    @Id
    private String username;
    private String password;
    private String name;

}
