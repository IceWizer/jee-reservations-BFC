package com.bfv.reservation.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfv.reservation.model.domain.location.Timezone;

public interface TimezoneRepository extends JpaRepository<Timezone, String> {

}
