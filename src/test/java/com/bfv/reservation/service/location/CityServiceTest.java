package com.bfv.reservation.service.location;

import com.bfv.reservation.model.domain.location.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CityServiceTest {
    @Autowired
    private CityService cityService;

    @Test
    void testFindAll() {
        // given

        // when
        List<City> cities = this.cityService.findAll();

        // then
        Assertions.assertNotNull(cities);
        Assertions.assertFalse(cities.isEmpty());
        Assertions.assertEquals(4, cities.size());
        Assertions.assertEquals("paris", cities.getFirst().getId());
    }

    @Test
    public void findByName() {
        //Given
        String name = "London";

        //When
        City foundCity = this.cityService.findByName(name).orElse(null);

        //Then
        assertNotNull(foundCity);
        assertEquals("london", foundCity.getId());
        assertEquals("United Kingdom", foundCity.getCountry().getName());
        assertEquals("Greenwich Meridian Time", foundCity.getTimezone().getName());
    }
}
