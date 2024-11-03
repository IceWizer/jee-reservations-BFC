package com.bfv.reservation.repository.hotel;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Assertions;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;
import com.bfv.reservation.repository.UserRepository;

@DataJpaTest
public class RoomTypeReservationRepositoryTest {

    @Autowired
    private RoomTypeReservationRepository roomTypeReservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetRoomTypeNumberAvailable() {
        String roomTypeId = "hotel-paris-single-room";

        // then
        Assertions.assertEquals(1, roomTypeReservationRepository.getRoomTypeNumberAvailable(roomTypeId, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 5)));
        Assertions.assertEquals(1, roomTypeReservationRepository.getRoomTypeNumberAvailable(roomTypeId, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 3)));
        Assertions.assertEquals(1, roomTypeReservationRepository.getRoomTypeNumberAvailable(roomTypeId, LocalDate.of(2020, 12, 30), LocalDate.of(2021, 1, 5)));
        Assertions.assertEquals(2, roomTypeReservationRepository.getRoomTypeNumberAvailable(roomTypeId, LocalDate.of(2020, 12, 30), LocalDate.of(2021, 1, 1)));
        Assertions.assertEquals(2, roomTypeReservationRepository.getRoomTypeNumberAvailable(roomTypeId, LocalDate.of(2021, 1, 5), LocalDate.of(2021, 1, 10)));
        Assertions.assertEquals(1, roomTypeReservationRepository.getRoomTypeNumberAvailable(roomTypeId, LocalDate.of(2021, 1, 2), LocalDate.of(2021, 1, 4)));
    }

    @Test
    public void testFindByUser() {
        String userId = "test_user-repository_find-by-email";
        User user = this.userRepository.findById(userId).get();

        // when
        List<RoomTypeReservation> roomTypeReservations = roomTypeReservationRepository.findByUser(user);

        // then
        Assertions.assertEquals(4, roomTypeReservations.size());
    }
}
