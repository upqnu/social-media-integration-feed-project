package com.example.socialmediafeed.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id")
    private String contentId;

    @Enumerated(EnumType.STRING)
    private TypeStatus type;

    @Column(name = "title")
    private String title;

    @Column(length = 20)
    private String content;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "share_count")
    private Integer shareCount;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Post (String contentId,
                 TypeStatus type,
                 String title,
                 String content,
                 Integer viewCount,
                 Integer likeCount,
                 Integer shareCount) {
        this.contentId = contentId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.shareCount = shareCount;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }
}
