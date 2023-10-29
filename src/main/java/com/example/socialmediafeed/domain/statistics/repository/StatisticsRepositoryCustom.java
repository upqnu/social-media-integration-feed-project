package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.post.entity.Post;

public interface StatisticsRepositoryCustom {

    // QueryDsl 사용 예시 todo : 예시를 위한 것으로, 지울 예정임
    Post findByIdCustom(Long id);
}
