package com.bfv.reservation.repository.flight;

import com.bfv.reservation.model.domain.flight.Airline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class AirlineRepositoryTest {
    @Autowired
    private AirlineRepository airlineRepository;

    @Test
    void testFindAll() {
        // given

        // when
        List<Airline> airlines = this.airlineRepository.findAll();

        // then
        assertNotNull(airlines);
        assertFalse(airlines.isEmpty());
        assertEquals(3, airlines.size());
        assertEquals("british-airways", airlines.get(1).getId());
    }

    @Test
    public void findByPlate() {
        //Given
        String icao = "AFR";

        //When
        Airline airline = this.airlineRepository.findByIcao(icao).orElse(null);

        //Then
        assertNotNull(airline);
        assertEquals("air-france", airline.getId());
        assertEquals("Air France", airline.getName());
        assertEquals("AF", airline.getCode());
        assertEquals("airbus-a320", airline.getAircraft().getId());
    }
}
