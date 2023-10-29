package com.example.socialmediafeed.domain.jwtoken.controller;

import com.example.socialmediafeed.domain.jwtoken.dto.CreateAccessTokenReqDto;
import com.example.socialmediafeed.domain.jwtoken.dto.CreateAccessTokenResDto;
import com.example.socialmediafeed.domain.jwtoken.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tokens")
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<CreateAccessTokenResDto> createNewAccessTokenUsingRefreshToken(
            @RequestBody CreateAccessTokenReqDto request) {
        String newAccessToken = tokenService.createNewAccessTokenUsingRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok(CreateAccessTokenResDto.of(newAccessToken));
    }
}
