package com.example.socialmediafeed.domain.hashtag.repository;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>, HashtagRepositoryCustom {
    boolean existsByName(String name);
}