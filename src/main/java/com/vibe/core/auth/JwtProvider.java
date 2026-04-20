package com.vibe.core.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * [Production-Ready] JWT ?좏겙 愿由ъ옄
 * ?ㅻТ?먯꽌 利됱떆 ?ъ슜 媛?ν븳 ?좏겙 ?앹꽦 諛?寃利?濡쒖쭅?낅땲??
 */
@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKeyPlain;
    private SecretKey secretKey;
    private final long tokenValidityInMilliseconds = 3600000 * 24; // 24?쒓컙

    @PostConstruct
    protected void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyPlain.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userId, String role) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidityInMilliseconds))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("?섎せ??JWT ?쒕챸?낅땲??");
        } catch (ExpiredJwtException e) {
            log.error("留뚮즺??JWT ?좏겙?낅땲??");
        } catch (UnsupportedJwtException e) {
            log.error("吏?먮릺吏 ?딅뒗 JWT ?좏겙?낅땲??");
        } catch (IllegalArgumentException e) {
            log.error("JWT ?좏겙???섎せ?섏뿀?듬땲??");
        }
        return false;
    }

    public String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}

