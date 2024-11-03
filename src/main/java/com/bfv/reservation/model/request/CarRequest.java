package com.bfv.reservation.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class CarRequest {
    @NotBlank
    private String brand;

    @NotBlank
    private String plate;

    @NotBlank
    private String motor;

    @NotBlank
    private String color;
}
