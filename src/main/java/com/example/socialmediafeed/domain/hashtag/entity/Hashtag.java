package com.example.socialmediafeed.domain.hashtag.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}