package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Post, Long>, StatisticsRepositoryCustom {


}
