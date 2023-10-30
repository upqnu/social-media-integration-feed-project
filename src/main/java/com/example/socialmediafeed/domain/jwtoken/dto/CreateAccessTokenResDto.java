package com.example.socialmediafeed.domain.jwtoken.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAccessTokenResDto {
    private String accessToken;

    public static CreateAccessTokenResDto of(String newAccessToken) {
        return new CreateAccessTokenResDto(newAccessToken);
    }
}
