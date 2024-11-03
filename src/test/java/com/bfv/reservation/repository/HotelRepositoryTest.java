package com.bfv.reservation.repository;

import com.bfv.reservation.repository.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;

    // Nothing to test here
    // All methods are inherited from JpaRepository
}
