package com.bfv.reservation.config;

import java.util.Date;

import javax.crypto.SecretKey;

import com.bfv.reservation.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final String KEY = "19D6IjLAudjoZMxFHnp/Owq2SKapi7JRqGhUo82TrAMF9JBz7ATG4SnDLulvQqI2";
    // private static final String KEY = RandomStringUtils.randomAlphanumeric(64);

    private JwtUtil() {
    }

    public static String generate(String email) {

        // Création de la clé de signature
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
        Date now = new Date();

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 36_000_000))
                .signWith(key)
                .compact();
    }

    public static boolean isValid(String token, UserService userService) {
        try {
            String email = JwtUtil.getEmail(token);
            userService.findByEmail(email).orElseThrow();

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String getEmail(String token) {
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
