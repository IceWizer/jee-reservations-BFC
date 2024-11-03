package com.bfv.reservation.service.hotel;

import com.bfv.reservation.model.domain.hotel.Hotel;
import com.bfv.reservation.model.domain.hotel.Room;
import com.bfv.reservation.repository.hotel.RoomRepository;
import com.bfv.reservation.service.GlobalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService extends GlobalService<Room, RoomRepository> {
    public RoomService(RoomRepository repository) {
        super(repository);
    }


    //Other methods
    public Optional<Room> getRoomByRoomNumber(Hotel hotel, String roomNumber) {
        return getRepository().findByRoomNumberAndHotel(roomNumber, hotel);
    }
}