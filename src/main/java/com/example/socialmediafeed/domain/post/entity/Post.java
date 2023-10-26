package com.example.socialmediafeed.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id")
    private String contentId;

    @Enumerated(EnumType.STRING)
    private TypeStatus type;

    private String title;

    @Column(length = 20)
    private String content;

    private Integer viewCount;

    private Integer likeCount;

    private Integer shareCount;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
