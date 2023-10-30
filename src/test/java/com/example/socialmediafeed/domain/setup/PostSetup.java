package com.example.socialmediafeed.domain.setup;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.entity.TypeStatus;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSetup {
    private final PostRepository postRepository;

    public PostSetup(PostRepository hashtagRepository) {
        this.postRepository = hashtagRepository;
    }

    public Post build() {
        return postRepository.save(buildPost());
    }

    public Post buildPost() {
        return Post.builder()
                .contentId(UUID.randomUUID().toString())
                .type(TypeStatus.TWITTER)
                .title("title")
                .content("content")
                .viewCount(0)
                .likeCount(0)
                .shareCount(0)
                .build();
    }
}
