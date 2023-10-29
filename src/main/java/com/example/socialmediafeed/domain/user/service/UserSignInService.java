package com.example.socialmediafeed.domain.user.service;

import com.example.socialmediafeed.domain.jwtoken.dto.CreateAccessTokenResDto;
import com.example.socialmediafeed.domain.user.dto.SignInReqDto;
import com.example.socialmediafeed.domain.user.entitiy.IsActive;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import com.example.socialmediafeed.global.config.jwt.TokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public CreateAccessTokenResDto signIn(SignInReqDto signInReqDto) {
        User user = userRepository.findByUsername(signInReqDto.getUsername())
                .orElseThrow(EntityNotFoundException::new);

        if(!encoder.matches(signInReqDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");

        if(user.getIsActive().equals(IsActive.DISABLED))
            throw new IllegalArgumentException("가입 승인이 되지 않은 계정입니다");

        // access token 생성 후 반환
        return CreateAccessTokenResDto.of(tokenProvider.generateToken(user, "access"));
    }

}
