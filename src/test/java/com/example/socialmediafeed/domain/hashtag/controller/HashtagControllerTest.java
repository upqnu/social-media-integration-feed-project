package com.example.socialmediafeed.domain.hashtag.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.setup.HashtagSetup;
import com.example.socialmediafeed.domain.setup.PostHashtagSetup;
import com.example.socialmediafeed.domain.setup.PostSetup;
import com.example.socialmediafeed.domain.setup.UserSetup;
import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.post.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HashtagControllerTest extends IntegrationTest {

    @Autowired
    UserSetup userSetup;
    @Autowired
    HashtagSetup hashtagSetup;
    @Autowired
    PostSetup postSetup;
    @Autowired
    PostHashtagSetup postHashtagSetup;
    @Test
    void getHotHashtag() throws Exception {
        //given
        Hashtag firstHashtag = hashtagSetup.build();
        Hashtag secondHashtag = hashtagSetup.build("#secondTest");
        Post firstPost = postSetup.build();
        Post secondPost = postSetup.build();
        Post thirdPost = postSetup.build();
        //when
        postHashtagSetup.build(firstPost, firstHashtag);
        postHashtagSetup.build(secondPost, firstHashtag);
        postHashtagSetup.build(thirdPost, secondHashtag);
        //then
        mvc.perform(
                        get("/hashtags/hot")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("hashtagName").value(firstHashtag.getName()))
                .andExpect(jsonPath("count").value(2L));

    }
}