package com.bfv.reservation.repository.flight;

import com.bfv.reservation.model.domain.flight.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, String> {
    Optional<Airline> findByIcao(String icao);
}
