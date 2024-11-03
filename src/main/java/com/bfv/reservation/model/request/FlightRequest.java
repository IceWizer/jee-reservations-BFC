package com.bfv.reservation.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FlightRequest {

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String departureAirportIATA;

    @NotBlank
    private String arrivalAirportIATA;

    @NotBlank
    private String airlineICAO;

    @NotBlank
    private LocalDateTime departureTime;

    @NotBlank
    private LocalDateTime arrivalTime;

    @NotBlank
    private double price;
}
