package com.bfv.reservation.service.airport;

import com.bfv.reservation.model.domain.flight.Aircraft;
import com.bfv.reservation.repository.flight.AircraftRepository;
import com.bfv.reservation.service.GlobalService;

public class AircraftService extends GlobalService<Aircraft, AircraftRepository> {
    public AircraftService(AircraftRepository repository) {
        super(repository);
    }
}
