package com.example.socialmediafeed.global.config.jwt;

import com.example.socialmediafeed.IntegrationTest;
import com.example.socialmediafeed.domain.setup.HashtagSetup;
import com.example.socialmediafeed.domain.setup.UserSetup;
import com.example.socialmediafeed.domain.user.entitiy.Authority;
import com.example.socialmediafeed.domain.user.entitiy.User;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

class TokenProviderTest extends IntegrationTest {

    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserSetup userSetup;
    @Autowired
    private HashtagSetup hashtagSetup;


    /**
     * 사용자 정보를 전달하여 토큰 생성
     */
    @Test
    void genenrateToken() {
        // given
        User testUser = userSetup.build(hashtagSetup.build());
        // when
        String testToken = tokenProvider.generateToken(testUser, "access");
        // then
        Long userId = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(testToken)
                .getBody()
                .get("userId", Long.class);

        Assert.assertEquals(userId, testUser.getId());
    }

    /**
     * 만료된 토큰은 유효성 검사 실패
     */
    @Test
    void invalidToken() {
        // given
        String testToken = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - 1000))
                .build()
                .createToken(secret);
        // when
        boolean result = tokenProvider.validToken(testToken);
        // then
        Assert.assertFalse(result);
    }

    /**
     * 토큰 기반으로 인증 정보 가져오기
     */
    @Test
    void getAuthentication() {
        // given
        String username = "testuser";
        String testToken = JwtFactory.builder()
                .subject(username)
                .claims(Map.of("auth", Authority.ROLE_MEMBER))
                .build()
                .createToken(secret);
        // when
        Authentication authentication = tokenProvider.getAuthentication(testToken);
        // then
        Assert.assertEquals(username, ((UserDetails) authentication.getPrincipal()).getUsername());
//        Assert.assertEquals(Authority.ROLE_MEMBER.toString(), authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()));
        Assert.assertEquals(Authority.ROLE_MEMBER.toString(), authentication.getAuthorities().toString().replaceAll("[\\['\\]]",""));
    }
}