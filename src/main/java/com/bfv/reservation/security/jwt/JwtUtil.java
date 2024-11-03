package com.bfv.reservation.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import com.bfv.reservation.service.UserService;

import javax.crypto.SecretKey;
import java.util.Date;

@Getter
public class JwtUtil {

    private static final String KEY = "19D6IjLAudjoZMxFHnp/Owq2SKapi7JRqGhUo82TrAMF9JBz7ATG4SnDLulvQqI2";

    public static String generate(String email) {
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
