package com.bfv.reservation.repository.hotel;

import com.bfv.reservation.model.domain.hotel.Hotel;
import com.bfv.reservation.model.domain.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findByRoomNumberAndHotel(String roomNumber, Hotel hotel);
}
