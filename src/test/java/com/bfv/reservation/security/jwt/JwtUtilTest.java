package com.bfv.reservation.security.jwt;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class JwtUtilTest {

    private static final String EMAIL = "test_user-repository_find-by-email@icewize.fr";

    @Test
    void shouldGenerateJwtToken() {
        // given
        Authentication authentication = this.createAuthentication();

        // when
        String token = JwtUtil.generate(authentication.getPrincipal().toString());
        String optUsername = JwtUtil.getEmail(token);

        // then
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(optUsername);
        Assertions.assertEquals(EMAIL, optUsername);
    }

    @Test
    void shouldTryValidateTokenThenFailed() {
        // given
        Authentication authentication = this.createAuthentication();

        // On transforme le token : algo "none" et suppression de la signature
        String token = "eyJhbGciOiJub25lIn0." + JwtUtil.generate(authentication.getPrincipal().toString()).split("\\.")[1] + ".";

        // when
        // then
        Assertions.assertThrows(UnsupportedJwtException.class, () -> JwtUtil.getEmail(token));
    }

    @Test
    void shouldTokenValidationFailed() {
        // given
        String token = "123456$";

        // when
        // then
        Assertions.assertThrows(MalformedJwtException.class, () -> JwtUtil.getEmail(token));
    }

    @Test
    void shouldNotValidateToken() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> JwtUtil.getEmail(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> JwtUtil.getEmail("  "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> JwtUtil.getEmail(null));
    }

    @Test
    void testInit() {
        Assertions.assertNotNull(new JwtUtil());
    }

    

    private Authentication createAuthentication() {
        return new UsernamePasswordAuthenticationToken(EMAIL, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
