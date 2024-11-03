package com.bfv.reservation.repo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bfv.reservation.model.domain.Car;
import com.bfv.reservation.repository.CarRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindAll() {
        // given

        // when
        List<Car> cars = this.carRepository.findAll();
        // then
        Assertions.assertNotNull(cars);
    }

    @Test
    @Transactional
    void testFindByPlate() {
        // given
        String plate = "aw-105-fr";
        Car car = new Car();
        car.setPlate(plate);
        this.carRepository.save(car);
        // when
        Optional<Car> carOptional = this.carRepository.findByPlate(plate);
        // then
        Assertions.assertNotNull(carOptional);
        Assertions.assertTrue(carOptional.isPresent());
        Assertions.assertEquals(plate, carOptional.get().getPlate());
    }
}
