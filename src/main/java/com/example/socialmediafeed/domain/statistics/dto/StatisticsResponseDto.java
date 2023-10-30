package com.example.socialmediafeed.domain.statistics.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsResponseDto {

    public LocalDateTime time;
    public Integer count;
}
