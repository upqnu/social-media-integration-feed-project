package com.example.socialmediafeed.domain.statistics.service;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;
import com.example.socialmediafeed.domain.statistics.repository.StatisticsRepository;
import com.example.socialmediafeed.domain.statistics.repository.StatisticsRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    /**
     * 통계 결과를 반환하는 메서드
     *
     * @return List<StatisticsResponseDto.
     */
    public List<StatisticsResponseDto> getStatistics(String hashtag, String start, String end, String type, String value) {

        // == dateTimeMap : 검색하는 시간 단위를 {시작:끝} 형태로 저장한 Map  == //
        Map<LocalDateTime, LocalDateTime> dateTimeMap = dateTimeMap(hashtag, start, end, type);

        List<StatisticsResponseDto> statisticsResponseDtoList = statisticsRepository.getStatistics(hashtag, dateTimeMap, value);
        return statisticsResponseDtoList;
    }

    /**
     * 검색하는 시간 단위를 {시작: 끝} 형태로 반환
     * @param hashtag
     * @param start
     * @param end
     * @param type
     * @return Map<LocalDateTime, LocalDateTime>
     */
    public Map<LocalDateTime, LocalDateTime> dateTimeMap(String hashtag, String start, String end, String type) {
        // todo : hashtag 기준 검색도 구현하기

        // == 날짜 목록 반환 == //
        List<LocalDate> DateTimeList = getDatesBetweenTwoDates(start, end);

        // == 시작과 끝을 시간 단위로 반환 == //
        Map<LocalDateTime, LocalDateTime> dateTimeMap = new HashMap<>();
        for (LocalDate date : DateTimeList) {

            // 하루 단위
            if (Objects.equals(type, "date")) {
                dateTimeMap.put(date.atStartOfDay(), date.atTime(LocalTime.MAX));
            }

            // 시간 단위
            else if (Objects.equals(type, "hour")) {
                LocalDateTime st = date.atStartOfDay();
                LocalDateTime fn = st.plusHours(1).minusNanos(1);
                for (int i = 0; i < 24; i++) {
                    dateTimeMap.put(st.plusHours(i), fn.plusHours(i));
                }
            }
        }

        return dateTimeMap;
    }

    /**
     * 날짜 구간(start, end)에 맞는 날짜 목록 반환
     * @param start
     * @param end
     * @return ["2023-10-25", "2023-10-26", "2023-10-27"]
     */
    private static List<LocalDate> getDatesBetweenTwoDates(String start, String end) {
        LocalDate startDateTime = start != null ? LocalDate.parse(start, DateTimeFormatter.ISO_DATE) : LocalDate.now().minusDays(7);
        LocalDate endDateTime = end != null ? LocalDate.parse(end, DateTimeFormatter.ISO_DATE) : LocalDate.now().atTime(LocalTime.MAX).toLocalDate();
        List<LocalDate> DateTimeList = startDateTime.datesUntil(endDateTime).collect(Collectors.toList());
        DateTimeList.add(endDateTime);
        return DateTimeList;
    }

}

