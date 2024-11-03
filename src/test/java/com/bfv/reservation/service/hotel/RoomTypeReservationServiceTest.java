package com.bfv.reservation.service.hotel;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;
import com.bfv.reservation.repository.UserRepository;

@SpringBootTest
public class RoomTypeReservationServiceTest {

    @Autowired
    private RoomTypeReservationService roomTypeReservationService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetRoomTypeNumberAvailable() {
        String roomTypeId = "hotel-paris-single-room";
        String roomTypeId2 = "hotel-paris-double-room";

        // then
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 5)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 3)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId, LocalDate.of(2020, 12, 30), LocalDate.of(2021, 1, 5)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId, LocalDate.of(2020, 12, 30), LocalDate.of(2021, 1, 1)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId, LocalDate.of(2021, 1, 5), LocalDate.of(2021, 1, 10)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId, LocalDate.of(2021, 1, 2), LocalDate.of(2021, 1, 4)));

        Assertions.assertFalse(roomTypeReservationService.isAvailable(roomTypeId2, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 5)));
        Assertions.assertFalse(roomTypeReservationService.isAvailable(roomTypeId2, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 3)));
        Assertions.assertFalse(roomTypeReservationService.isAvailable(roomTypeId2, LocalDate.of(2020, 12, 30), LocalDate.of(2021, 1, 5)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId2, LocalDate.of(2020, 12, 30), LocalDate.of(2021, 1, 1)));
        Assertions.assertTrue(roomTypeReservationService.isAvailable(roomTypeId2, LocalDate.of(2021, 1, 5), LocalDate.of(2021, 1, 10)));
        Assertions.assertFalse(roomTypeReservationService.isAvailable(roomTypeId2, LocalDate.of(2021, 1, 2), LocalDate.of(2021, 1, 4)));
    }

    @Test
    public void testFindByUser() {
        String userId = "test_user-repository_find-by-email";
        User user = this.userRepository.findById(userId).get();

        // when
        List<RoomTypeReservation> roomTypeReservations = roomTypeReservationService.findByUser(user);

        // then
        Assertions.assertEquals(4, roomTypeReservations.size());
    }
}
