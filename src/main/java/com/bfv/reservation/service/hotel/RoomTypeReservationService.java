package com.bfv.reservation.service.hotel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;
import com.bfv.reservation.repository.hotel.RoomTypeReservationRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class RoomTypeReservationService extends GlobalService<RoomTypeReservation, RoomTypeReservationRepository> {

    public RoomTypeReservationService(RoomTypeReservationRepository repository) {
        super(repository);
    }

    public void cancel(String reservationId) {
        RoomTypeReservation reservation = getRepository().findById(reservationId).orElse(null);
        if (reservation != null) {
            reservation.setState("cancelled");
            getRepository().save(reservation);
        }
    }

    public boolean isAvailable(String roomTypeId, LocalDate fromDate, LocalDate toDate) {
        return getRepository().getRoomTypeNumberAvailable(roomTypeId, fromDate, toDate) > 0;
    }

    public List<RoomTypeReservation> findByUser(User user) {
        return getRepository().findByUser(user);
    }
}
