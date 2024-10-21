package com.bfv.reservation.repository;

import com.bfv.reservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
}
