package com.bfv.reservation.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
    private LocalDate departureTime;

    @NotBlank
    private LocalDate arrivalTime;

    @NotBlank
    private double price;

    private int availableSeats;
}