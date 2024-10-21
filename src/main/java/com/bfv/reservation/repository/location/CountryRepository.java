package com.bfv.reservation.repository.location;

import com.bfv.reservation.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}