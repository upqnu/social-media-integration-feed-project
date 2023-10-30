package com.example.socialmediafeed.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DummyDataLoader implements ApplicationRunner {

    private final DummyDataService dummyDataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dummyDataService.createDummyPosts();
    }
}
