package com.bfv.reservation.service.location;

import com.bfv.reservation.model.domain.location.City;
import com.bfv.reservation.repository.location.CityRepository;
import com.bfv.reservation.service.GlobalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService extends GlobalService<City, CityRepository> {

    public CityService(CityRepository repository) {
        super(repository);
    }

    public Optional<City> findByName(String name) {
        return getRepository().findByName(name);
    }
}
