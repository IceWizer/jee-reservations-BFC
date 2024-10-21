package com.bfv.reservation.repository.airport;

import com.bfv.reservation.model.airport.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftModelRepository extends JpaRepository<Aircraft, String> {
}
