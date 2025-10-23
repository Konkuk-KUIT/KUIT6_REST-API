package com.example.kuit_9week_mission.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
@Getter
public class JwtUtil {
    private final SecretKey key;
    private final long accessTokenExpirationMs;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration-ms}") long accessTokenExpirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpirationMs = accessTokenExpirationMs;
    }

    // 액세스 토큰 발급
    public String generateAccessToken(Long studentId) {
        return generateToken(studentId, accessTokenExpirationMs);
    }

    private String generateToken(Long studentId, long expireMs) {
        Instant now = Instant.now();
        Instant exp = now.plusMillis(expireMs);

        return Jwts.builder()
                .header().type("JWT").and()
                .subject(studentId.toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key)
                .compact();
    }

    public Long getStudentId(String token) {
        String sub = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();

        return Long.parseLong(sub);
    }
}
