package com.example.socialmediafeed.domain.post.repository;

import com.example.socialmediafeed.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

    @Query("SELECT p FROM Post p WHERE lower(p.content) LIKE %:hashtag% ESCAPE ' '")
    List<Post> findByContentContainingHashtag(@Param("hashtag") String hashtag);
}
