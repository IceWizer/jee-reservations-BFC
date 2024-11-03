package com.bfv.reservation.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bfv.reservation.model.domain.Flight;

@DataJpaTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testFindByFlightNumber() {
        // Import via data.sql
        String flightNumber = "AF123";
        // Given

        // When
        Flight foundFlight = this.flightRepository.findByFlightNumber(flightNumber).orElse(null);

        // Then
        Assertions.assertNotNull(foundFlight);
        Assertions.assertEquals("paris-berlin", foundFlight.getId());
        Assertions.assertEquals("AF123", foundFlight.getFlightNumber());
        Assertions.assertEquals("paris-charles-de-gaulle", foundFlight.getDepartureAirport().getId());
        Assertions.assertEquals("berlin-tegel", foundFlight.getArrivalAirport().getId());
        Assertions.assertEquals("air-france", foundFlight.getAirline().getId());
        Assertions.assertNotNull(foundFlight.getDepartureTime());
        Assertions.assertNotNull(foundFlight.getArrivalTime());
        Assertions.assertEquals(2.5, foundFlight.getDuration());
        Assertions.assertEquals(100.0, foundFlight.getPrice());
    }
}
