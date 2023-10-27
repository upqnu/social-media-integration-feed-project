package com.example.socialmediafeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SocialMediaFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaFeedApplication.class, args);
    }

}
