package com.bfv.reservation.security.jwt;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class JwtHeaderFilterTest {
    private static final String EMAIL = "test_user-repository_find-by-email@icewize.fr";

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtHeaderFilter jwtFilter;

    @Test
    void shouldAuthorizeThenAdmin() throws ServletException, IOException {
        // given
        Authentication authentication = this.createAuthentication();
        User user = new User();

        user.setEmail(EMAIL);
        user.setAdmin(true);

        String token = JwtUtil.generate(authentication);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));

        // when
        jwtFilter.doFilter(request, response, filterChain);

        // then
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.verify(filterChain).doFilter(request, response);
        Assertions.assertNotNull(authentication);
        Assertions.assertEquals(1, authentication.getAuthorities().size());
        Assertions.assertEquals("ROLE_ADMIN", Objects.requireNonNull(authentication.getAuthorities().stream().findFirst().orElse(null)).getAuthority());
    }

    @Test
    void shouldAuthorizeThenUser() throws ServletException, IOException {
        // given
        Authentication authentication = this.createAuthentication();
        User user = new User();

        user.setEmail(EMAIL);
        user.setAdmin(false);

        String token = JwtUtil.generate(authentication);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));

        // when
        jwtFilter.doFilter(request, response, filterChain);

        // then
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.verify(filterChain).doFilter(request, response);
        Assertions.assertNotNull(authentication);
        Assertions.assertEquals(1, authentication.getAuthorities().size());
        Assertions.assertEquals("ROLE_USER", Objects.requireNonNull(authentication.getAuthorities().stream().findFirst().orElse(null)).getAuthority());
    }

    @Test
    void shouldNotAuthorize() throws ServletException, IOException {
        // given
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer the.wrong.token");
        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());

        // when
        jwtFilter.doFilter(request, response, filterChain);

        // then
        Mockito.verify(filterChain).doFilter(request, response);
        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private Authentication createAuthentication() {
        return new UsernamePasswordAuthenticationToken(
                EMAIL,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
