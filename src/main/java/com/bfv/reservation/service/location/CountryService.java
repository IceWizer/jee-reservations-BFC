package com.bfv.reservation.service.location;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.location.Country;
import com.bfv.reservation.repository.location.CountryRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class CountryService extends GlobalService<Country, CountryRepository> {

    public CountryService(CountryRepository repository) {
        super(repository);
    }
}
