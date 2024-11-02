package com.bfv.reservation.service.location;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.location.Timezone;
import com.bfv.reservation.repository.location.TimezoneRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class TimezoneService extends GlobalService<Timezone, TimezoneRepository> {

    public TimezoneService(TimezoneRepository repository) {
        super(repository);
    }
}
