package com.example.socialmediafeed.domain.hashtag.repository;

import com.example.socialmediafeed.domain.hashtag.dto.HotHashtagResDto;

import java.time.LocalDateTime;
import java.util.Optional;

public interface HashtagRepositoryCustom {
    Optional<HotHashtagResDto> findHotHashtag(LocalDateTime from, LocalDateTime to);
}
