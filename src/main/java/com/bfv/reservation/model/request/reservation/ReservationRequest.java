package com.bfv.reservation.model.request.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {
    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;


    @NotBlank
    private String departureFlightNumber;

    @NotBlank
    private String returnFlightNumber;


    @NotBlank
    private String userId;

    @NotBlank
    private boolean paid;
}
