package com.bfv.reservation.repository.flight;

import com.bfv.reservation.model.domain.flight.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, String> {
    Optional<Airport> findByIata(String iata);
}
