package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.socialmediafeed.domain.post.entity.QPost.post;

@RequiredArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepositoryCustom{
    private final JPAQueryFactory queryFactory;


    // QueryDsl 사용 예시 todo : 예시를 위한 것으로, 지울 예정임
    @Override
    public Post findByIdCustom(Long id) {
        return queryFactory
                .selectFrom(post)
                .where(post.id.eq(id))
                .fetchFirst();
    }

    @Override
    public List<StatisticsResponseDto> DateLikeCount() {
        System.out.println("repository");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        List<LocalDateTime> dateTimeList = new ArrayList<>();
        List<StatisticsResponseDto> statisticsResponseDtoList = new ArrayList<>();
        try{
            // for문 돌릴 date List
            LocalDateTime date1 = LocalDateTime.parse("2023-10-29-00-00-00", formatter);
            LocalDateTime date2 = LocalDateTime.parse("2023-10-29-00-00-00", formatter);
            dateTimeList.add(date1);
            dateTimeList.add(date2);

            for(LocalDateTime dateTime : dateTimeList) {
                System.out.println("dateTime1");
                LocalDateTime start = LocalDateTime.of(2023, 10,29,0,0,0);
                LocalDateTime end = LocalDateTime.of(2023,10,29,23,59,59);
                int dateCount = queryFactory
                        .select(post.likeCount.sum())
                        .from(post)
                        .where(post.createdAt.between(start, end))
                        .fetchFirst();
                statisticsResponseDtoList.add(StatisticsResponseDto.builder()
                        .time(dateTime)
                        .count(dateCount)
                        .build());
            }
        } catch (Exception e) {
            //hello
        }
        return statisticsResponseDtoList;
    }


}
