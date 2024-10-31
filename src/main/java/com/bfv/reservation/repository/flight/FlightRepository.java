package com.bfv.reservation.repository.flight;

import com.bfv.reservation.model.domain.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {
    Optional<Flight> findByFlightNumber(String flightNumber);
}
