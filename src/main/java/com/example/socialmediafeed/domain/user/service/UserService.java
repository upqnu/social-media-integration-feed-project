package com.example.socialmediafeed.domain.user.service;

import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import com.example.socialmediafeed.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    // todo: exception을 business exception으로 갈아야 합니다.
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Unexpected userId"));
    }

    /**
     * User 정보 얻기
     * @param token
     * @return
     */
    public User findUserByAccessToken(String token) {
        return findUserById(tokenProvider.getUserId(token));
    }
}
