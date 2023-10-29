package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
}
