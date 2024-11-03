package com.bfv.reservation.repository.location;

import com.bfv.reservation.model.domain.car.Car;
import com.bfv.reservation.model.domain.location.City;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    void testFindAll() {
        // given

        // when
        List<City> cities = this.cityRepository.findAll();

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
        City foundCity = this.cityRepository.findByName(name).orElse(null);

        //Then
        assertNotNull(foundCity);
        assertEquals("london", foundCity.getId());
        assertEquals("United Kingdom", foundCity.getCountry().getName());
        assertEquals("Greenwich Meridian Time", foundCity.getTimezone().getName());
    }
}
