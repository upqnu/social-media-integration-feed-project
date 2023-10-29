package com.example.socialmediafeed.domain.statistics.controller;

import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;
import com.example.socialmediafeed.domain.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// 통계 관련 API
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    /**
     * 통계 반환 메서드
     *
     * @param hashtag
     * @param type
     * @param start
     * @param end
     * @param value
     * @return List<StatisticsResponseDto>
     */
    @GetMapping
    @ResponseBody // todo : ResponseEntity로 변경
    public List<StatisticsResponseDto> getStatistics(
            @RequestParam(required = false) String hashtag,
            @RequestParam String type, // date, hour 둘 중 하나
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) String value) {
        return statisticsService.getStatistics(hashtag, start, end, type, value);
    }
}
