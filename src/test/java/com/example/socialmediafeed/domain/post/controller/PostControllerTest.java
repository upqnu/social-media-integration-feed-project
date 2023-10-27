package com.example.socialmediafeed.domain.post.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testGetPosts() throws Exception {
        // given
        when(postService.getAllPosts()).thenReturn(new ArrayList<>());

        // when
        mockMvc.perform(get("/posts"))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPostsByHashtag() throws Exception {
        // given
        String hashtag = "#java";
        List<Post> posts = new ArrayList<>();

        Pageable pageable = Pageable.unpaged();
        Page<Post> page = new PageImpl<>(posts);

        when(postService.findPostByTitleAndContent(any(), any())).thenReturn(page);

        // when
        mockMvc.perform(get("/posts?search=" + hashtag))
                // then
                .andExpect(status().isOk());
    }
}