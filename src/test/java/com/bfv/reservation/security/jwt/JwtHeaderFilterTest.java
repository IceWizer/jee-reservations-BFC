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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.bfv.reservation.service.UserService;

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
    private UserService userService;

    @InjectMocks
    private JwtHeaderFilter jwtFilter;

    @Test
    void shouldAuthorizeThenAdmin() throws ServletException, IOException {
        // given
        User user = new User();
        user.setEmail(EMAIL);
        user.setAdmin(true);

        String token = JwtUtil.generate(EMAIL); // Generate the token with the correct email
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));

        // when
        jwtFilter.doFilter(request, response, filterChain);

        // then
        Mockito.verify(filterChain).doFilter(request, response);

        // Assert that the SecurityContext has been populated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertNotNull(authentication, "Authentication should not be null after filter");
        Assertions.assertEquals(1, authentication.getAuthorities().size());
        Assertions.assertEquals("ROLE_ADMIN", authentication.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void shouldAuthorizeThenUser() throws ServletException, IOException {
        // given
        User user = new User();
        user.setEmail(EMAIL);
        user.setAdmin(false); // Non-admin user

        String token = JwtUtil.generate(EMAIL); // Generate the token with the correct email
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));

        // when
        jwtFilter.doFilter(request, response, filterChain);
        Mockito.verify(filterChain).doFilter(request, response);

        // then
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertNotNull(authentication, "Authentication should not be null after filter");
        Assertions.assertEquals(1, authentication.getAuthorities().size());
        Assertions.assertEquals("ROLE_USER", authentication.getAuthorities().iterator().next().getAuthority());
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
