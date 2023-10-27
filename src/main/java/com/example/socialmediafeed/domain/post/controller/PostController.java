package com.example.socialmediafeed.domain.post.controller;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<Page<Post>> getPosts(
            @RequestParam(required = false) String hashtag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int page_count,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        List<Post> allPosts = postService.getAllPosts();

        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, page_count, sort);

        Page<Post> postsList;

        if (hashtag == null) {
            postsList = new PageImpl<>(allPosts, pageable, allPosts.size());
        } else {
            postsList = postService.findPostByTitleAndContent(hashtag, pageable);
            if (postsList.getNumberOfElements() == 0) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body((Page<Post>) Map.of("message", "게시물을 찾을 수 없습니다."));
            }
        }

        return ResponseEntity.ok(postsList);
    }

}
