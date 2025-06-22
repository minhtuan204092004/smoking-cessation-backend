package com.example.backend.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expiration-ms}") private long expiry;

    public String generateToken(Long userId) {
        Date now = new Date();
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + expiry))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
    public Long getUserId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token).getBody().getSubject());
    }
    public boolean validate(String token) {
        try { Jwts.parser().setSigningKey(secret).parseClaimsJws(token); return true; }
        catch (JwtException|IllegalArgumentException e) { return false; }
    }
}