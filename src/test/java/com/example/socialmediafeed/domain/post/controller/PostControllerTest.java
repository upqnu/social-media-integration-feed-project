package com.example.socialmediafeed.domain.post.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.SocialMediaFeedApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialMediaFeedApplication.class)
@AutoConfigureMockMvc
@Transactional
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
        assertEquals(204, status);
    }
}
