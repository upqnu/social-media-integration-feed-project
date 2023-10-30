package com.example.socialmediafeed.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInReqDto {
    @NotNull
    private String username;
//    @Email(message = "이메일 형식에 맞게 작성해 주세요")
//    private String email;
    @NotNull
    private String password;

    public static SignInReqDto of(String username, String password) {
        return new SignInReqDto(username, password);
    }
}
