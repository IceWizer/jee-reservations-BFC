package com.bfv.reservation.model.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
