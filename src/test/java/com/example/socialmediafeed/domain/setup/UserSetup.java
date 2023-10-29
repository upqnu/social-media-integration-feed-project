package com.example.socialmediafeed.domain.setup;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserSetup {
    private final UserRepository userRepository;

    public UserSetup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User build(Hashtag hashtag) {
        return userRepository.save(buildUser("test@email.com", hashtag));
    }

    public User buildUser(String email, Hashtag hashtag) {
        return User.builder()
                .email(email)
                .password("1q2w3e4r!")
                .certificationNumber(RandomStringUtils.random(5, true, true))
                .hashtag(hashtag)
                .build();
    }
}
