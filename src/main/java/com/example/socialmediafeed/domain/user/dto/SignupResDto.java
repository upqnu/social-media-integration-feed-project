package com.example.socialmediafeed.domain.user.dto;

import com.example.socialmediafeed.domain.user.entitiy.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupResDto {
    private long id;
    private String email;
    private String hashtag;

    public static SignupResDto from(User user) {
        return new SignupResDto(
                user.getId(),
                user.getEmail(),
                user.getHashtag().getName()
        );
    }

}