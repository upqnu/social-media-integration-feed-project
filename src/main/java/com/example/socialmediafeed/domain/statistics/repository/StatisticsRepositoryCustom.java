package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;

import java.util.List;

public interface StatisticsRepositoryCustom {

    // QueryDsl 사용 예시 todo : 예시를 위한 것으로, 지울 예정임
    Post findByIdCustom(Long id);

    // 날짜별로 Like_Count의 합계를 반환함
    List<StatisticsResponseDto> DateLikeCount();
}
