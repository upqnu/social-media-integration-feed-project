package com.example.socialmediafeed.domain.user.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.user.dto.SignupReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiControllerTest extends IntegrationTest {

    @Test
    void sign_up_success() throws Exception {
        //given
        String email = "test@naver.com";
        String password = "1q2w3e4r!";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(email, password, hashtag);
        //when
        getSignupResultAction(request)
        //then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("hashtag").value(hashtag));

    }

    @Test
    void sign_up_email_valid() throws Exception {
        //given
        String email = "test#naver.com";
        String password = "1q2w3e4r!";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(email, password, hashtag);
        //when
        getSignupResultAction(request)
        //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_only_integer_valid() throws Exception {
        //given
        String email = "test#naver.com";
        String password = "1234567890";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(email, password, hashtag);
        //when
        getSignupResultAction(request)
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_length_valid() throws Exception {
        //given
        String email = "test#naver.com";
        String password = "1q2w3e";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(email, password, hashtag);
        //when
        getSignupResultAction(request)
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    private ResultActions getSignupResultAction(SignupReqDto request) throws Exception {
        return mvc.perform(
                post("/users/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );
    }

}