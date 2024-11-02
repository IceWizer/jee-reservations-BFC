package com.bfv.reservation.service.airport;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.airport.Aircraft;
import com.bfv.reservation.repository.airport.AircraftRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class AircraftService extends GlobalService<Aircraft, AircraftRepository> {

    public AircraftService(AircraftRepository repository) {
        super(repository);
    }
}
