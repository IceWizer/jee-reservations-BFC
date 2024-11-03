package com.bfv.reservation.repository.flight;

import com.bfv.reservation.model.domain.flight.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, String> {
}
