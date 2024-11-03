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
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Reservation extends PersistentEntity {
    @UuidGenerator
    private String reservationNumber;

    private LocalDate startDate;
    private LocalDate endDate;

    private double totalPrice;
    private boolean paid = false;
    private boolean active = true;

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
