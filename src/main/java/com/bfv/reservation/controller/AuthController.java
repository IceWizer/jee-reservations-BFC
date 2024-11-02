package com.bfv.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bfv.reservation.config.JwtUtil;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.request.AuthRequest;
import com.bfv.reservation.model.request.SignInRequest;
import com.bfv.reservation.model.response.Auth.TokenResponse;
import com.bfv.reservation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public TokenResponse auth(@RequestBody AuthRequest request) {
        // On va demander à SPRING SECURITY d'authentifier l'utilisateur
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenResponse response = new TokenResponse();
            // On va générer un token JWT
            response.setToken(JwtUtil.generate(request.getEmail()));

            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public String subscribe(@RequestBody SignInRequest request) {
        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.userRepository.save(user);

        return user.getId();
    }
}
