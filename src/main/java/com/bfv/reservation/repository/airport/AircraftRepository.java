package com.bfv.reservation.repository.airport;

import com.bfv.reservation.model.domain.airport.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, String> {
}
