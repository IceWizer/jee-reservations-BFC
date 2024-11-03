package com.bfv.reservation.service.flight;

import com.bfv.reservation.model.domain.flight.Airline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AirlineServiceTest {
    @Autowired
    private AirlineService airlineService;

    @Test
    void testFindAll() {
        // given

        // when
        List<Airline> airlines = this.airlineService.findAll();

        // then
        assertNotNull(airlines);
        assertFalse(airlines.isEmpty());
        assertEquals(3, airlines.size());
        assertEquals("american-airlines", airlines.get(2).getId());
    }

    @Test
    public void findByPlate() {
        //Given
        String icao = "AFR";

        //When
        Airline airline = this.airlineService.getAirlineByIcao(icao).orElse(null);

        //Then
        assertNotNull(airline);
        assertEquals("air-france", airline.getId());
        assertEquals("Air France", airline.getName());
        assertEquals("AF", airline.getCode());
        assertEquals("airbus-a320", airline.getAircraft().getId());
    }
}
