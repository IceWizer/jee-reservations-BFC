package com.bfv.reservation.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class JwtUtilTest {
    private static final String EMAIL = "test_user-repository_find-by-email@icewize.fr";

    @Test
    void shouldGenerateJwtToken() {
        // given
        Authentication authentication = this.createAuthentication();

        // when
        String token = JwtUtil.generate(authentication);
        Optional<String> optUsername = JwtUtil.getEmail(token);

        // then
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(optUsername);
        Assertions.assertTrue(optUsername.isPresent());
        Assertions.assertEquals(EMAIL, optUsername.get());
    }

    @Test
    void shouldTryValidateTokenThenFailed() {
        // given
        Authentication authentication = this.createAuthentication();
        String token = JwtUtil.generate(authentication);

        // On transforme le token : algo "none" et suppression de la signature
        token = "eyJhbGciOiJub25lIn0." + token.split("\\.")[1] + ".";

        // when
        Optional<String> optUsername = JwtUtil.getEmail(token);

        // then
        Assertions.assertNotNull(optUsername);
        Assertions.assertFalse(optUsername.isPresent());
    }

    @Test
    void shouldTokenValidationFailed() {
        // given
        String token = "123456$";

        // when
        Optional<String> optUsername = JwtUtil.getEmail(token);

        // then
        Assertions.assertNotNull(optUsername);
        Assertions.assertFalse(optUsername.isPresent());
    }

    @Test
    void shouldNotValidateToken() {
        Assertions.assertFalse(JwtUtil.getEmail("").isPresent());
        Assertions.assertFalse(JwtUtil.getEmail("  ").isPresent());
        Assertions.assertFalse(JwtUtil.getEmail(null).isPresent());
    }

    private Authentication createAuthentication() {
        return new UsernamePasswordAuthenticationToken(EMAIL, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
