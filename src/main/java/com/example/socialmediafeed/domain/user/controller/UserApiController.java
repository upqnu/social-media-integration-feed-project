package com.example.socialmediafeed.domain.user.controller;

import com.example.socialmediafeed.domain.user.dto.SignupReqDto;
import com.example.socialmediafeed.domain.user.service.UserSignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserApiController {

    private final UserSignupService userSignupService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody
                                         @Valid
                                         SignupReqDto signupReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignupService.signUp(signupReqDto));
    }


}