package com.bfv.reservation.service.hotel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bfv.reservation.model.domain.hotel.Hotel;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @Test
    public void testGetRoomByRoomNumber() {
        // given
        String roomNumber = "302";
        String hotelId = "hotel-london";

        Hotel hotel = hotelService.findById(hotelId).orElse(null);

        // when
        var room = roomService.getRoomByRoomNumber(hotel, roomNumber).orElse(null);

        // then
        Assertions.assertNotNull(room);
        Assertions.assertEquals(roomNumber, room.getRoomNumber());
        Assertions.assertEquals(hotelId, room.getHotel().getId());
    }

}
