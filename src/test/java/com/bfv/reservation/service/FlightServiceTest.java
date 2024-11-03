package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.flight.Flight;
import com.bfv.reservation.service.flight.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    public void testFindByFlightNumber() {
        // Import via data.sql
        String flightNumber = "AF123";
        // Given

        // When
        Flight foundFlight = this.flightService.getFlightByFlightNumber(flightNumber).orElse(null);

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
