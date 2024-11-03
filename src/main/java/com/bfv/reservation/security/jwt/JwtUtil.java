package com.bfv.reservation.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Getter
public class JwtUtil {
    private static final String KEY = "19D6IjLAudjoZMxFHnp/Owq2SKapi7JRqGhUo82TrAMF9JBz7ATG4SnDLulvQqI2";

    public static String generate(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
        Date now = new Date();

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 36_000_000))
                .signWith(key)
                .compact();
    }

    public static Optional<String> getEmail(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());

            return Optional.ofNullable(
                    Jwts.parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
                            .getSubject()
            );
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
