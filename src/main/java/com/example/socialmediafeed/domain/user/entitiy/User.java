package com.example.socialmediafeed.domain.user.entitiy;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "user")
@Entity
public class User {
    @Id
    public Long id;
}
