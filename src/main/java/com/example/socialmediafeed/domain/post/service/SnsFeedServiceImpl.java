package com.example.socialmediafeed.domain.post.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SnsFeedServiceImpl implements SnsFeedService {

    @Override
    public void searchSns() {
        log.info("전송이 성공하였습니다.");
    }
}
