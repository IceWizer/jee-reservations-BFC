package com.bfv.reservation.repository.airport;

import com.bfv.reservation.model.domain.airport.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {

}
