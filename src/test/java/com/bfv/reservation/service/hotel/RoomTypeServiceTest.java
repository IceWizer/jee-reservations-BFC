package com.bfv.reservation.service.hotel;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bfv.reservation.model.domain.hotel.RoomType;

@SpringBootTest
public class RoomTypeServiceTest {

    @Autowired
    private RoomTypeService roomTypeService;

    @Test
    public void testFindByHotelId() {
        String hotelIdLength3 = "hotel-paris";
        String hotelIdLenght0 = "hotel-london";

        // Given
        // When
        List<RoomType> foundRoomType = this.roomTypeService.findByHotelId(hotelIdLength3);
        List<RoomType> foundRoomType2 = this.roomTypeService.findByHotelId(hotelIdLenght0);

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
