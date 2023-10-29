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
        // type는 date또는 hour이 올 수 있다.
        Map<LocalDateTime, LocalDateTime> dateTimeMap = dateTimeList(hashtag, start, end, type);
        List<StatisticsResponseDto> statisticsResponseDtoList = statisticsRepository.getStatistics(hashtag, dateTimeMap, value);
        return statisticsResponseDtoList;
    }

    public Map<LocalDateTime, LocalDateTime> dateTimeList(String hashtag, String start, String end, String type) {

        Map<LocalDateTime, LocalDateTime> unitDateTimeList = new HashMap<>();

        // 날짜 구간 설정
        LocalDate startDateTime = LocalDate.now().minusDays(7);
        LocalDate endDateTime = LocalDate.now().atTime(LocalTime.MAX).toLocalDate();
        if (start != null) {
            startDateTime = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
        }
        if (end != null) {
            endDateTime = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
        }

        // 날짜별 시작 시간과 종료 시간을 LocalDateTime으로
        List<LocalDate> localDateList = getDatesBetweenTwoDates(startDateTime, endDateTime);
        for (LocalDate date : localDateList) {
            if (Objects.equals(type, "date")) {
                unitDateTimeList.put(date.atStartOfDay(), date.atTime(LocalTime.MAX));
            } else if (Objects.equals(type, "hour")) {
                LocalDateTime st = date.atStartOfDay();
                LocalDateTime fn = st.plusHours(1).minusNanos(1);
                for (int i = 0; i < 24; i++) {
                    unitDateTimeList.put(st.plusHours(i), fn.plusHours(i));
                }
            }
        }

        // 날짜별
        return unitDateTimeList;
    }

    private static List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> getDatesBetweenTwoDates = startDate.datesUntil(endDate).collect(Collectors.toList());
        getDatesBetweenTwoDates.add(endDate);
        return getDatesBetweenTwoDates;
    }

}

