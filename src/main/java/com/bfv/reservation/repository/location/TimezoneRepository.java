package com.bfv.reservation.repository.location;

import com.bfv.reservation.model.location.Timezone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimezoneRepository extends JpaRepository<Timezone, String> {

}
