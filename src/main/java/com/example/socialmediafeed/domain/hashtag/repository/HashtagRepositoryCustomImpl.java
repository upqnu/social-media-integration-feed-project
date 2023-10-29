package com.example.socialmediafeed.domain.hashtag.repository;

import com.example.socialmediafeed.domain.hashtag.dto.HotHashtagResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.socialmediafeed.domain.hashtag.entity.QHashtag.hashtag;
import static com.example.socialmediafeed.domain.posthashtag.entity.QPostHashtag.postHashtag;

@RequiredArgsConstructor
public class HashtagRepositoryCustomImpl implements HashtagRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<HotHashtagResDto> findHotHashtag(LocalDateTime from, LocalDateTime to) {

        return Optional.of(
                jpaQueryFactory.select(
                                Projections.constructor(
                                        HotHashtagResDto.class,
                                        hashtag.name,
                                        hashtag.count()
                                )
                        )
                .from(hashtag)
                .innerJoin(postHashtag).on(hashtag.id.eq(postHashtag.hashtag.id))
                .where(postHashtag.createdAt.between(from, to))
                .groupBy(hashtag.name)
                .orderBy(hashtag.name.count().desc()).limit(1)
                .fetchOne()
        );

    }
}