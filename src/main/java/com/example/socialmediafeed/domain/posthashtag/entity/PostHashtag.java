package com.example.socialmediafeed.domain.posthashtag.entity;

import com.example.socialmediafeed.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "post_hashtag_id")
    private List<Post> taggedPosts;
}
