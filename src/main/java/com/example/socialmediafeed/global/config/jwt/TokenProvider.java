package com.example.socialmediafeed.global.config.jwt;

import com.example.socialmediafeed.domain.user.entitiy.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final String SECRET;
    // access token 유효시간 (millisecond)
    private final long ACCESS_TOKEN_DURATION_MS;
    // refresh token 유효시간 (millisecond)
    private final long REFRESH_TOKEN_DURATION_MS;
    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration.access}") long accessTokenDuration,
            @Value("${jwt.expiration.refresh}") long refreshTokenDuration) {
        this.SECRET = secret;
        this.ACCESS_TOKEN_DURATION_MS = accessTokenDuration * 1000;
        this.REFRESH_TOKEN_DURATION_MS = refreshTokenDuration * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 토큰 생성 : 유저와 생성할 토큰 타입을 변수로 받음.
     * @param user User 객체
     * @param tokenType String : "access" or "refresh"
     */
    public String generateToken(User user, String tokenType) {
        Date now = new Date();
        Date expiry = tokenType.equals("access") ? new Date(now.getTime() + ACCESS_TOKEN_DURATION_MS) : new Date(now.getTime() + REFRESH_TOKEN_DURATION_MS);

        return createToken(user, expiry);
    }

    private String createToken(User user, Date expiry) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(expiry)
                .setIssuedAt(new Date())
                // 권한, email, id 값 저장
                .addClaims(Map.of(
                        AUTHORITIES_KEY, user.getAuthority(),
                        "email", user.getEmail(),
                        "userId", user.getId())
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        Set<SimpleGrantedAuthority> authority = Collections.singleton(
                new SimpleGrantedAuthority(claims.get(AUTHORITIES_KEY).toString())
        );

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authority),
                token,
                authority
        );
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
