package com.bfv.reservation.controller;

import com.bfv.reservation.exception.DuplicateElement;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.request.user.AuthRequest;
import com.bfv.reservation.model.response.domain.AuthResponse;
import com.bfv.reservation.security.service.AuthService;
import com.bfv.reservation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.bfv.reservation.Library.generateID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse saveUser(@Valid @RequestBody AuthRequest request) {
        User user = new User();
        user.setId(generateID());

        if (userService.getEmails().contains(request.getEmail())) {
            throw new DuplicateElement("Email");
        }

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
