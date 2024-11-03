package com.bfv.reservation.service.car;

import com.bfv.reservation.model.domain.car.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class CarServiceTest {
    @Autowired
    private CarService carService;

    @Test
    void testFindAll() {
        // given

        // when
        List<Car> cars = this.carService.findAll();

        // then
        Assertions.assertNotNull(cars);
        Assertions.assertFalse(cars.isEmpty());
        Assertions.assertEquals(3, cars.size());
        Assertions.assertEquals("car-1", cars.getFirst().getId());
    }

    @Test
    public void findByPlate() {
        //Given
        String plateNumber = "AB-123-CD";

        //When
        Car foundCar = this.carService.getCarByPlate(plateNumber).orElse(null);

        //Then
        assertNotNull(foundCar);
        assertEquals("car-1", foundCar.getId());
        assertEquals("Ford", foundCar.getBrand());
        assertEquals("black", foundCar.getColor());
        assertEquals("1.6", foundCar.getMotor());
    }
}
