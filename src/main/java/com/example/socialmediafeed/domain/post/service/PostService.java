package com.example.socialmediafeed.domain.post.service;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts;
    }

    public Page<Post> findPostByTitleAndContent(String hashtag, Pageable pageable) {
        return postRepository.findByContentContainingIgnoreCase(hashtag, pageable);
    }

}
