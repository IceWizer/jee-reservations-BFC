package com.bfv.reservation.repository.hotel;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bfv.reservation.model.domain.hotel.RoomType;

@DataJpaTest
public class RoomTypeRepositoryTest {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Test
    public void testFindByHotelId() {
        String hotelIdLength3 = "hotel-paris";
        String hotelIdLenght0 = "hotel-london";

        // Given
        // When
        List<RoomType> foundRoomType = this.roomTypeRepository.findByHotelId(hotelIdLength3);
        List<RoomType> foundRoomType2 = this.roomTypeRepository.findByHotelId(hotelIdLenght0);

        // Then
        Assertions.assertNotNull(foundRoomType);
        Assertions.assertEquals(3, foundRoomType.size());
        for (RoomType roomType : foundRoomType) {
            Assertions.assertEquals(hotelIdLength3, roomType.getHotel().getId());
        }

        Assertions.assertNotNull(foundRoomType2);
        Assertions.assertEquals(0, foundRoomType2.size());
    }
}
