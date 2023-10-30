package com.example.socialmediafeed.domain.user.repository;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepoForUser extends JpaRepository<Hashtag, Long> {
    boolean existsByName(String name);
}
