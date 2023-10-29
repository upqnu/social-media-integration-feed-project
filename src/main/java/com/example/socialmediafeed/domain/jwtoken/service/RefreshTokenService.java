package com.example.socialmediafeed.domain.jwtoken.service;

import com.example.socialmediafeed.domain.jwtoken.entity.RefreshToken;
import com.example.socialmediafeed.domain.jwtoken.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    // todo: exception을 business exception으로 갈아야 합니다.
    public RefreshToken findRefreshTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
