package com.bfv.reservation.repository;

import com.bfv.reservation.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

}
