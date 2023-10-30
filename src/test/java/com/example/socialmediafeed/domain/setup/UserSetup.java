package com.example.socialmediafeed.domain.setup;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserSetup {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

//    public UserSetup(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User build(Hashtag hashtag) {
        return userRepository.save(buildUser("testuser", "test@email.com", hashtag));
    }

    public User approvedBuild(Hashtag hashtag) {
        User testuser = buildUser("testuser", "test@email.com", hashtag);
        testuser.activeAccount();
        return userRepository.save(testuser);
    }

    public User buildUser(String username, String email, Hashtag hashtag) {
        return User.builder()
                .username(username)
                .email(email)
                .password(encoder.encode("1q2w3e4r!"))
                .certificationNumber(RandomStringUtils.random(5, true, true))
                .hashtag(hashtag)
                .build();
    }
}
