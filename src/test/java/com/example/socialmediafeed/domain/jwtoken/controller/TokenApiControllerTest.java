package com.example.socialmediafeed.domain.jwtoken.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.jwtoken.dto.CreateAccessTokenReqDto;
import com.example.socialmediafeed.domain.jwtoken.entity.RefreshToken;
import com.example.socialmediafeed.domain.jwtoken.repository.RefreshTokenRepository;
import com.example.socialmediafeed.domain.setup.HashtagSetup;
import com.example.socialmediafeed.domain.setup.UserSetup;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.global.config.jwt.JwtFactory;
import com.example.socialmediafeed.global.config.jwt.TokenAuthenticationFilter;
import com.example.socialmediafeed.global.config.jwt.TokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TokenApiControllerTest extends IntegrationTest {

    // 역할 및 활용에 대해 확실하게 알아야함.
//    @Autowired
//    private WebApplicationContext context;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserSetup userSetup;
    @Autowired
    private HashtagSetup hashtagSetup;
    @Value("${jwt.secret}")
    private String secret;

    // 역할 및 활용에 대해 확실하게 알아야함.
//    @BeforeEach
//    public void mockMvcSetup() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    @Test
    void createNewAccessTokenUsingRefreshToken() throws Exception {
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

    @Test
    void adminPermissionVaild() throws Exception {
        // given
        User testUser = userSetup.approvedBuild(hashtagSetup.build());

        String token = tokenProvider.generateToken(testUser, "access");
        // when
        mvc.perform(
                get("/tokens/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(TokenAuthenticationFilter.HEADER_AUTHORIZATION, TokenAuthenticationFilter.TOKEN_PREFIX + token)
        )
                // then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}