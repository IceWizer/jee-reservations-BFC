package com.bfv.reservation.security.service;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.request.user.CreateUserRequest;
import com.bfv.reservation.model.response.domain.AuthResponse;
import com.bfv.reservation.security.jwt.JwtUtil;
import com.bfv.reservation.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.bfv.reservation.Library.USER;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthResponse auth(CreateUserRequest authRequest) {
        String message;

        try {
            log.debug("Trying to authenticate ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userService.getUserByEmail(authRequest.getEmail()).orElseThrow(() -> new NotFound(USER, authRequest.getEmail()));

            log.debug("Successfully authenticated!");

            return AuthResponse.builder()
                    .message("Successfully authenticated!")
                    .token(JwtUtil.generate(authentication))
                    .id(user.getId())
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
