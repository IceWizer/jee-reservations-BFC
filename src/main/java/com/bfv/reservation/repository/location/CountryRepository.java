package com.bfv.reservation.repository.location;

import com.bfv.reservation.model.domain.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}