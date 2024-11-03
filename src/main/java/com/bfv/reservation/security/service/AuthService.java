package com.bfv.reservation.security.service;

import com.bfv.reservation.model.request.user.AuthRequest;
import com.bfv.reservation.model.response.domain.AuthResponse;
import com.bfv.reservation.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public AuthResponse auth(AuthRequest authRequest) {
        String message;

        try {
            log.debug("Trying to authenticate ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("Successfully authenticated!");

            return AuthResponse.builder()
                    .message("Successfully authenticated!")
                    .token(JwtUtil.generate(authentication))
                    .build();
        } catch (BadCredentialsException ex) {
            message = "Can't authenticate : bad credentials.";
        } catch (Exception ex) {
            message = "Can't authenticate : unknown error (" + ex.getClass().getSimpleName() + ").";
        }

        return AuthResponse.builder()
                .message(message)
                .build();
    }
}
