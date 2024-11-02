package com.bfv.reservation.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
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
