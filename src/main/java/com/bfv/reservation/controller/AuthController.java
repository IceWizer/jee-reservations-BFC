package com.bfv.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.bfv.reservation.Library.generateID;
import com.bfv.reservation.exception.DuplicateElement;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.request.user.AuthRequest;
import com.bfv.reservation.model.response.domain.AuthResponse;
import com.bfv.reservation.security.service.AuthService;
import com.bfv.reservation.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse saveUser(@Valid @RequestBody AuthRequest request) {
        if (userService.hasEmail(request.getEmail())) {
            throw new DuplicateElement("Email");
        }

        User user = new User();
        user.setId(generateID());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);

        return authService.auth(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.auth(request);
    }
}
