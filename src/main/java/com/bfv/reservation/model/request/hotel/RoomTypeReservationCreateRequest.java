package com.bfv.reservation.model.request.hotel;

import java.time.LocalDate;

import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;
import com.bfv.reservation.service.hotel.RoomTypeService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeReservationCreateRequest {

    private String roomTypeId;
    private LocalDate fromDate;
    private LocalDate toDate;

    public RoomTypeReservation toRoomTypeReservation(RoomTypeService roomTypeService) {
        RoomTypeReservation reservation = new RoomTypeReservation();
        roomTypeService.findById(this.roomTypeId).ifPresent(reservation::setRoomType);
        reservation.setFromDate(this.fromDate);
        reservation.setToDate(this.toDate);
        return reservation;
    }
}
