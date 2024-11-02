package com.bfv.reservation.model.request.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationCarRequest {
    @NotBlank
    private String carId;
}
