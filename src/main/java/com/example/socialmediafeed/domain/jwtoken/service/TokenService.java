package com.example.socialmediafeed.domain.jwtoken.service;

import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.service.UserService;
import com.example.socialmediafeed.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessTokenUsingRefreshToken(String refreshToken) {
        // refresh token이 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findRefreshTokenByRefreshToken(refreshToken).getUserId();
        User user = userService.findUserById(userId);

        return tokenProvider.generateToken(user, "access");
    }
}
