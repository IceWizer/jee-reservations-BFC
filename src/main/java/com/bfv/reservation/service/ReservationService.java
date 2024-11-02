package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.Reservation;
import com.bfv.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService extends GlobalService<Reservation, ReservationRepository> {
    public ReservationService(ReservationRepository repository) {
        super(repository);
    }


    //Other methods
    public Optional<Reservation> getReservationByReservationNumber(String reservationNumber) {
        return getRepository().findByReservationNumber(reservationNumber);
    }
}
