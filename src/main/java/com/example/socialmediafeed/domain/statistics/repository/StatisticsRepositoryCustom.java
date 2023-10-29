package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface StatisticsRepositoryCustom {

    // 날짜별로 Like_Count의 합계를 반환함
    List<StatisticsResponseDto> getStatistics(String hashtag, Map<LocalDateTime, LocalDateTime> dateTimeMap, String value);
}
