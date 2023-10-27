package com.example.socialmediafeed.domain.posthashtag.entity;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

}
