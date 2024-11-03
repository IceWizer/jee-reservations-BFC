package com.bfv.reservation.model.domain;

import com.bfv.reservation.model.domain.airport.Airline;
import com.bfv.reservation.model.domain.airport.Airport;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Flight extends PersistentEntity {

    private String flightNumber;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Airline airline;

    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private double duration;

    private double price;
}
