package com.bfv.reservation.controller;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.request.user.UserRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.model.response.domain.UserResponse;
import com.bfv.reservation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.bfv.reservation.Library.USER;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController extends BuilderResponse<UserResponse> {
    private final UserService userService;

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

    @PutMapping("/create/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BasicResponse updateUser(@PathVariable String id, @Valid @RequestBody UserRequest request) {
        return save(userService.findById(id).orElseThrow(() -> new NotFound(USER, id)), request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
