package com.example.socialmediafeed.domain.setup;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.hashtag.repository.HashtagRepository;
import org.springframework.stereotype.Component;

@Component
public class HashtagSetup {
    private final HashtagRepository hashtagRepository;

    public HashtagSetup(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public Hashtag build() {
        return hashtagRepository.save(buildHashtag("#test"));
    }

    public Hashtag build(String hashtagName) {
        return hashtagRepository.save(buildHashtag(hashtagName));
    }

    public Hashtag buildHashtag(String name) {
        return Hashtag.builder()
                .name(name)
                .build();
    }
}
