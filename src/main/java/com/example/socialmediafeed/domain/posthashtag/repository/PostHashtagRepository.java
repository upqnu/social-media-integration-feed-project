package com.example.socialmediafeed.domain.posthashtag.repository;

import com.example.socialmediafeed.domain.posthashtag.entity.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {
}
