package com.example.socialmediafeed.domain.hashtag.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotHashtagResDto {
    private String hashtagName;
    private Long count;

    public HotHashtagResDto(String hashtagName, Long count) {
        this.hashtagName = hashtagName;
        this.count = count;
    }

}
