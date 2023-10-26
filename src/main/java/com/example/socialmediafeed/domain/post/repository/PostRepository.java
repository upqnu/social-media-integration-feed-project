package com.example.socialmediafeed.domain.post.repository;

import com.example.socialmediafeed.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}