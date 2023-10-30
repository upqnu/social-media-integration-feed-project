package com.example.socialmediafeed.domain.jwtoken.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAccessTokenReqDto {
    private String refreshToken;

    public static CreateAccessTokenReqDto of(String refreshToken) {
        return new CreateAccessTokenReqDto(refreshToken);
    }
}
