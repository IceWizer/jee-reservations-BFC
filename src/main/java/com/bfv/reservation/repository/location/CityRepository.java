package com.bfv.reservation.repository.location;

import com.bfv.reservation.model.domain.location.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, String> {
    Optional<City> findByName(String name);
}
