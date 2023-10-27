package com.example.socialmediafeed.domain.statistics.service;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;
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

    /**
     * 통계 결과를 반환하는 메서드
     * @return List<StatisticsResponseDto.
     */
    public List<StatisticsResponseDto> getStatistics(){
        // todo : Request Parameter 기준으로 StatisticsResponseDto 반환하도록 변경(현재는 dummy data형식)
        StatisticsResponseDto statisticsResponseDto = StatisticsResponseDto.builder().time(LocalDateTime.now()).count(1).build();
        List<StatisticsResponseDto> statisticsResponseDtoList = new ArrayList<>();
        statisticsResponseDtoList.add(statisticsResponseDto);
        return statisticsResponseDtoList;
    }


}
