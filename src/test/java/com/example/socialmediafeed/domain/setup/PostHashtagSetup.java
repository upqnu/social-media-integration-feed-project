package com.example.socialmediafeed.domain.setup;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.posthashtag.entity.PostHashtag;
import com.example.socialmediafeed.domain.posthashtag.repository.PostHashtagRepository;
import org.springframework.stereotype.Component;

@Component
public class PostHashtagSetup {
    private final PostHashtagRepository postHashtagRepository;

    public PostHashtagSetup(PostHashtagRepository postHashtagRepository) {
        this.postHashtagRepository = postHashtagRepository;
    }

    public PostHashtag build(Post post, Hashtag hashtag) {
        return postHashtagRepository.save(buildPostHashtag(post, hashtag));
    }

    public PostHashtag buildPostHashtag(Post post, Hashtag hashtag) {
        return PostHashtag.builder()
                .post(post)
                .hashtag(hashtag)
                .build();
    }

}
