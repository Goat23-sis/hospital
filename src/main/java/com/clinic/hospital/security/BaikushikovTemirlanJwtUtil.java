package com.clinic.hospital.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class BaikushikovTemirlanJwtUtil {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "BaikushikovTemirlanHospitalSecretKeyForJWT2026ExamProject".getBytes(StandardCharsets.UTF_8)
    );
    private final long EXPIRATION_TIME = 86400000; // 24 часа

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", "ROLE_" + role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}