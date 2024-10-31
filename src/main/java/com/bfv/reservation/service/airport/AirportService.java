package com.bfv.reservation.service.airport;

import com.bfv.reservation.model.domain.flight.Airport;
import com.bfv.reservation.repository.flight.AirportRepository;
import com.bfv.reservation.service.GlobalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportService extends GlobalService<Airport, AirportRepository> {
    public AirportService(AirportRepository repository) {
        super(repository);
    }

    public Optional<Airport> getAirportByIata(String iata) {
        return getRepository().findByIata(iata);
    }
}
