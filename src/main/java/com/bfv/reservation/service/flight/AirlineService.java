package com.bfv.reservation.service.flight;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.flight.Airline;
import com.bfv.reservation.repository.flight.AirlineRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class AirlineService extends GlobalService<Airline, AirlineRepository> {

    public AirlineService(AirlineRepository repository) {
        super(repository);
    }

    public Optional<Airline> getAirlineByIcao(String icao) {
        return getRepository().findByIcao(icao);
    }
}
