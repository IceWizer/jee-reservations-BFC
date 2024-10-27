package com.bfv.reservation.service.location;

import com.bfv.reservation.model.domain.location.Timezone;
import com.bfv.reservation.repository.location.TimezoneRepository;
import com.bfv.reservation.service.GlobalService;

public class TimezoneService extends GlobalService<Timezone, TimezoneRepository> {
    public TimezoneService(TimezoneRepository repository) {
        super(repository);
    }
}
