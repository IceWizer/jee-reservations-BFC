package com.bfv.reservation.repository.hotel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bfv.reservation.model.domain.hotel.Hotel;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testFindByRoomNumberAndHotel() {
        // given
        String roomNumber = "302";
        String hotelId = "hotel-london";

        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);

        // when
        var room = roomRepository.findByRoomNumberAndHotel(roomNumber, hotel).orElse(null);

        // then
        Assertions.assertNotNull(room);
        Assertions.assertEquals(roomNumber, room.getRoomNumber());
        Assertions.assertEquals(hotelId, room.getHotel().getId());
    }
}
