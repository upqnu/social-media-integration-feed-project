package com.example.socialmediafeed.domain.post.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.post.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class PostControllerTest extends IntegrationTest {

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
    void countLikesOnPost() throws Exception {
        Long postId = 5L;
        Logger logger = Logger.getLogger(getClass().getName());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/posts/" + postId + "/likes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Post post = objectMapper.readValue(content, Post.class);
        int initialLikeCount = post.getLikeCount();

        logger.info("Initial likeCount: " + initialLikeCount);
    }
}
