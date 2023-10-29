package com.example.socialmediafeed.domain.user.controller;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.HashtagSetup;
import com.example.socialmediafeed.domain.UserSetup;
import com.example.socialmediafeed.domain.user.dto.ApprovalReqDto;
import com.example.socialmediafeed.domain.user.dto.SignInReqDto;
import com.example.socialmediafeed.domain.user.dto.SignupReqDto;
import com.example.socialmediafeed.domain.user.entitiy.User;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiControllerTest extends IntegrationTest {

    @Autowired
    UserSetup userSetup;
    @Autowired
    HashtagSetup hashtagSetup;

    // -- sign up test

    @Test
    void sign_up_success() throws Exception {
        //given
        String username = "test123";
        String email = "test@naver.com";
        String password = "1q2w3e4r!";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
        //when
        getSignupResultAction(request)
        //then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("username").value(username))
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("hashtag").value(hashtag));

    }

    @Test
    void sign_up_email_valid() throws Exception {
        //given
        String username = "test123";
        String email = "test#naver.com";
        String password = "1q2w3e4r!";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
        //when
        getSignupResultAction(request)
        //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_only_integer_valid() throws Exception {
        //given
        String username = "test123";
        String email = "test#naver.com";
        String password = "1234567890";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
        //when
        getSignupResultAction(request)
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_length_valid() throws Exception {
        //given
        String username = "test123";
        String email = "test#naver.com";
        String password = "1q2w3e";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
        //when
        getSignupResultAction(request)
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_username_blank_valid() throws Exception {
        //given
        String username = "  ";
        String email = "test#naver.com";
        String password = "1234567890";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
        //when
        getSignupResultAction(request)
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_username_length_valid() throws Exception {
        //given
        String username = "abcde";
        String email = "test#naver.com";
        String password = "1234567890";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
        //when
        getSignupResultAction(request)
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void sign_up_username_symbol_valid() throws Exception {
        //given
        String username = "!@#$%^&";
        String email = "test#naver.com";
        String password = "1234567890";
        String hashtag = "#test";

        SignupReqDto request = SignupReqDto.of(username, email, password, hashtag);
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

    // -- END sign up test

    // -- sign in test

    @Test
    void sign_in_success() throws Exception {
        // given
        User user = userSetup.approvedBuild(hashtagSetup.build());

        SignInReqDto reqDto = SignInReqDto.of(user.getUsername(), "1q2w3e4r!");
        // when
        mvc.perform(
                post("/users/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqDto))
        )
                // then
                .andDo(print())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

    @Test
    void sign_in_password_vaild() throws Exception {
        // given
        User user = userSetup.approvedBuild(hashtagSetup.build());

        SignInReqDto reqDto = SignInReqDto.of(user.getUsername(), "1q1q1q1q!");
        // then
        String error = Assertions.assertThrows(ServletException.class, () -> {
            // when
            mvc.perform(
                    post("/users/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(reqDto))
            );
        }).getMessage();
        System.out.println(error);
    }

    @Test
    void sign_in_approval_vaild() throws Exception {
        // given
        User user = userSetup.build(hashtagSetup.build());

        SignInReqDto reqDto = SignInReqDto.of(user.getUsername(), "1q2w3e4r!");
        // then
        String error = Assertions.assertThrows(ServletException.class, () -> {
            // when
            mvc.perform(
                    post("/users/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(reqDto))
            );
        }).getMessage();
        System.out.println(error);
    }

    // -- END sign in test

    // -- approval test

    @Test
    void approval_success() throws Exception {
        //given
        User user = userSetup.build(hashtagSetup.build());
        //when
        ApprovalReqDto reqDto = ApprovalReqDto.from(user.getCertificationNumber());
        //then
        mvc.perform(
                patch("/users/{id}/approval", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqDto))
        )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    // -- END approval test

}