package com.bfv.reservation.repository.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TimezoneRepositoryTest {

    @Autowired
    private TimezoneRepository timezoneRepository;

    // Nothing to test here
    // All methods are inherited from JpaRepository
}
