package com.bfv.reservation.security.service;

import com.bfv.reservation.model.request.user.AuthRequest;
import com.bfv.reservation.model.response.domain.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthServiceTest {
    private static final String EMAIL = "test_user-repository_find-by-email@icewize.fr";
    private static final String PASSWORD = "password";

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService service;

    @Test
    void shouldReturnSuccessTrueWithToken() {
        // given
        Authentication authentication = new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD);
        Mockito.when(this.authenticationManager.authenticate(authentication)).thenReturn(authentication);

        AuthRequest request = AuthRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        // when
        AuthResponse response = this.service.auth(request);

        // then
        Mockito.verify(this.authenticationManager).authenticate(Mockito.any());
        assertNotNull(response);
        assertEquals("Successfully authenticated!", response.getMessage());
        assertNotNull(response.getToken());
    }

    @Test
    void shouldReturnSuccessFalseWithoutToken() {
        // given
        Authentication authentication = new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD);
        Mockito.when(this.authenticationManager.authenticate(authentication)).thenReturn(null);

        AuthRequest request = AuthRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        // when
        AuthResponse response = this.service.auth(request);

        // then
        Mockito.verify(this.authenticationManager).authenticate(Mockito.any());
        assertNotNull(response);
        assertNull(response.getToken());
    }
}
