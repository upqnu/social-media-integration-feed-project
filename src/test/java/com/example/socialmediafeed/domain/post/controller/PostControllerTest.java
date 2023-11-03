package com.example.socialmediafeed.domain.post.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import com.example.socialmediafeed.domain.setup.PostSetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends IntegrationTest {

    @Autowired
    PostSetup postSetup;
    @Autowired
    PostRepository postRepository;

    @Test
    public void testTotalPostsCount() throws Exception {
        String uri = "/posts";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        int totalElements = (int) new ObjectMapper().readValue(content, Map.class).get("totalElements");

        assertEquals(20, totalElements);
    }

    @Test
    public void testGetPosts() throws Exception {

        String uri = "/posts";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                        .param("hashtag", "java")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                        .param("hashtag", "soccer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testGetPostById() throws Exception {
        Long postId = 20L;
        Logger logger = Logger.getLogger(getClass().getName());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/posts/" + postId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Post post = objectMapper.readValue(content, Post.class);
        int initialViewCount = post.getViewCount();

        logger.info("Initial viewCount: " + initialViewCount);
    }

    @Test
    void post_like_count_plus_success() throws Exception {
        //given
        Post post = postSetup.build();
        assertEquals(post.getLikeCount(), 0);
        //when
        //then
        mvc.perform(
                get("/posts/{id}/likes", post.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk());
        post = postRepository.findById(post.getId()).get();
        assertEquals(post.getLikeCount(), 1);
    }

    @Test
    void countSharesOnPost() throws Exception {
        Long postId = 10L;
        Logger logger = Logger.getLogger(getClass().getName());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/posts/" + postId + "/shares")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Post post = objectMapper.readValue(content, Post.class);
        int initialShareCount = post.getShareCount();

        logger.info("Initial shareCount: " + initialShareCount);
    }
}
