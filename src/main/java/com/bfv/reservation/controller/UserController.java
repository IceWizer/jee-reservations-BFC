package com.bfv.reservation.controller;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.request.user.CreateUserRequest;
import com.bfv.reservation.model.request.user.UserRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.model.response.domain.AuthResponse;
import com.bfv.reservation.model.response.domain.UserResponse;
import com.bfv.reservation.security.service.AuthService;
import com.bfv.reservation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.bfv.reservation.Library.USER;
import static com.bfv.reservation.Library.generateID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController extends BuilderResponse<UserResponse> {
    private final UserService userService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ListResponse<UserResponse> getUsers() {
        return getListResponse(userService.findAll().stream().map(this::buildResponse).toList());
    }

    @GetMapping("/id/{id}")
    public DataResponse<UserResponse> getUserById(@PathVariable String id) {
        return getDataResponse(buildResponse(userService.findById(id).orElseThrow(() -> new NotFound(USER, id))), USER);
    }

    @GetMapping("/email/{email}")
    public DataResponse<UserResponse> getUserByEmail(@PathVariable String email) {
        return getDataResponse(buildResponse(userService.getUserByEmail(email).orElseThrow(() -> new NotFound(USER, email))), USER);
    }

    @PostMapping("/create")
    public BasicResponse saveUser(@Valid @RequestBody CreateUserRequest request) {
        User user = new User();
        user.setId(generateID());

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return save(USER, userService.save(user));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody CreateUserRequest request) {
        return authService.auth(request);
    }

    @PutMapping("create/{id}")
    public BasicResponse updateUser(@PathVariable String id, @Valid @RequestBody UserRequest request) {
        return save(userService.findById(id).orElseThrow(() -> new NotFound(USER, id)), request);
    }

    @DeleteMapping("/{id}")
    public BasicResponse deleteUser(@PathVariable String id) {
        return delete(userService.delete(id));
    }


    private BasicResponse save(User user, UserRequest request) {
        BeanUtils.copyProperties(request, user);

        return save(USER, userService.save(user));
    }


    private UserResponse buildResponse(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);

        return response;
    }
}
