package com.bfv.reservation.model.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
