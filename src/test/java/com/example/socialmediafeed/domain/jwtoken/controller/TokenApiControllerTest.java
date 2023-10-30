package com.example.socialmediafeed.domain.jwtoken.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.jwtoken.dto.CreateAccessTokenReqDto;
import com.example.socialmediafeed.domain.jwtoken.entity.RefreshToken;
import com.example.socialmediafeed.domain.jwtoken.repository.RefreshTokenRepository;
import com.example.socialmediafeed.domain.setup.HashtagSetup;
import com.example.socialmediafeed.domain.setup.UserSetup;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.global.config.jwt.JwtFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TokenApiControllerTest extends IntegrationTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserSetup userSetup;
    @Autowired
    private HashtagSetup hashtagSetup;
    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    public void mockMvcSetup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createNewAccessTokenUsingRefreshToken() throws Exception {
        // given
        User testUser = userSetup.build(hashtagSetup.build());

        String refreshToken = JwtFactory.builder()
                .claims(Map.of("email",testUser.getEmail()))
                .build()
                .createToken(secret);

        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        CreateAccessTokenReqDto request = CreateAccessTokenReqDto.of(refreshToken);

        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mvc.perform(
                post("/tokens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
//        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
}