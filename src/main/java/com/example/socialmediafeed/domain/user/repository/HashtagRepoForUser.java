package com.example.socialmediafeed.domain.user.repository;

import com.example.socialmediafeed.domain.hashtag.repository.HashtagRepository;

public interface HashtagRepoForUser extends HashtagRepository {
    boolean existsByName(String name);
}
