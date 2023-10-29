package com.example.socialmediafeed.domain.statistics.service;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;
import com.example.socialmediafeed.domain.statistics.repository.StatisticsRepository;
import com.example.socialmediafeed.domain.statistics.repository.StatisticsRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final PostRepository postRepository;
    private final StatisticsRepository statisticsRepository;

    /**
     * 통계 결과를 반환하는 메서드
     * @return List<StatisticsResponseDto.
     */
    public List<StatisticsResponseDto> getStatistics(){

        // 1. 모든 글에 대해서 하루 단위로 조회하기
        // hashtag : 일단 이 부분 제외하기
        // start : 일단 이 부분 제외하기
        // end : 일단 이 부분 제외하기
        // count : like_count 기준으로 조회하기(2, 3번은 이 부분을 바꿀 예정)
        // 예상되는 결과 => {"2023-10-01" : 9, "2023-10-02" : 10, "2023-10-03" : 10}
        // Post에서 조회하자. 2023-10-29기준으로
        List<StatisticsResponseDto> statisticsResponseDtoList = statisticsRepository.DateLikeCount();
        return statisticsResponseDtoList;
    }


}

