package com.bfv.reservation.service.airport;

import com.bfv.reservation.model.domain.airport.Airline;
import com.bfv.reservation.repository.airport.AirlineRepository;
import com.bfv.reservation.service.GlobalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineService extends GlobalService<Airline, AirlineRepository> {
    public AirlineService(AirlineRepository repository) {
        super(repository);
    }

    public Optional<Airline> getAirlineByIcao(String icao) {
        return getRepository().findByIcao(icao);
    }
}
