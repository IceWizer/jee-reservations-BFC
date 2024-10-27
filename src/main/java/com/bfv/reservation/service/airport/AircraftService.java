package com.bfv.reservation.service.airport;

import com.bfv.reservation.model.domain.airport.Aircraft;
import com.bfv.reservation.repository.airport.AircraftRepository;
import com.bfv.reservation.service.GlobalService;

public class AircraftService extends GlobalService<Aircraft, AircraftRepository> {
    public AircraftService(AircraftRepository repository) {
        super(repository);
    }
}
