package com.example.socialmediafeed.global.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Getter;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/// JWT 서비스 테스트에 사용하기 위한 Mocking 객체
@Getter
public class JwtFactory {
    // 만료시간 10분
    private Date expiration = new Date(new Date().getTime() + 600 * 1000);
    private Date issuedAt = new Date();
    private Map<String, Object> claims = Collections.emptyMap();

    private String subject = "testuser";

    /**
     * 빌더 패턴을 사용해 테스트 시 설정이 필요한 데이터만 선택 설정
     * @param subject 토큰 제목 (사용자의 unique 데이터 사용)
     * @param issuedAt 토큰 발행 시간
     * @param expiration 토큰 만료 시간
     * @param claims 추가적인 클레임 ("auth" 등등)
     */
    @Builder
    public JwtFactory(String subject, Date issuedAt, Date expiration, Map<String, Object> claims) {
        this.subject = subject != null ? subject : this.subject;
        this.issuedAt = issuedAt != null ? issuedAt : this.issuedAt;
        this.expiration = expiration != null ? expiration : this.expiration;
        this.claims = claims != null ? claims : this.claims;
    }

    public static JwtFactory withDefaultValues() {
        return JwtFactory.builder().build();
    }

    public String createToken(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiration)
                .setIssuedAt(issuedAt)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}