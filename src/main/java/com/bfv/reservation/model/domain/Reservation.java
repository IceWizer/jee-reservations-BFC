package com.bfv.reservation.model.domain;

import com.bfv.reservation.model.domain.car.Car;
import com.bfv.reservation.model.domain.flight.Flight;
import com.bfv.reservation.model.domain.hotel.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Reservation extends PersistentEntity {
    private String reservationNumber;

    private LocalDate startDate;
    private LocalDate endDate;

    private double totalPrice;
    private boolean paid;
    private boolean active;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Flight departureFlight;

    @ManyToOne
    private Flight returnFlight;
}
