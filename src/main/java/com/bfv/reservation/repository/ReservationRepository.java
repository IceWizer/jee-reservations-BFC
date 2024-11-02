package com.bfv.reservation.repository;

import com.bfv.reservation.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    Optional<Reservation> findByReservationNumber(String reservationNumber);
}
