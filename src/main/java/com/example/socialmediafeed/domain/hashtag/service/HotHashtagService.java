package com.example.socialmediafeed.domain.hashtag.service;

import com.example.socialmediafeed.domain.hashtag.dto.HotHashtagResDto;
import com.example.socialmediafeed.domain.hashtag.repository.HashtagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class HotHashtagService {

    private final HashtagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public HotHashtagResDto getHotHashtag() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeHoursAgo = now.minusHours(3);

        return hashtagRepository.findHotHashtag(threeHoursAgo, now)
                .orElseThrow(EntityNotFoundException::new);
    }
}
