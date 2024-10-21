package com.bfv.reservation.repository.airport;

import com.bfv.reservation.model.domain.airport.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, String> {

}
