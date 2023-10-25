package com.example.socialmediafeed.domain.content.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "content")
@Entity
public class Content {
    @Id
    private Long id;
}
