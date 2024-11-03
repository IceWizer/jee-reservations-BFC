package com.bfv.reservation.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.Car;
import com.bfv.reservation.repository.CarRepository;


@SpringBootTest(classes = CarService.class)
@ActiveProfiles("test")
public class CarServiceTest {
    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Test
    void shouldReturnCarByPlate() {
        // given
        Mockito.when(carRepository.findByPlate(Mockito.anyString())).thenReturn(Optional.of(new Car()));
        // when
        Assertions.assertNotNull(this.carService.getCarByPlate("plate"));
        // then
        Mockito.verify(this.carRepository, Mockito.times(1)).findByPlate(Mockito.anyString());
    }

    @Test
    void shouldThrowExceptionOnCarByPlate() {
        // given

        // when
        Assertions.assertThrows(NotFound.class, () -> {
            this.carService.getCarByPlate("");
        });

        Assertions.assertThrows(NotFound.class, () -> {
            this.carService.getCarByPlate(null);
        });
        // then
        Mockito.verify(this.carRepository, Mockito.never()).findByPlate(null);
        Mockito.verify(this.carRepository, Mockito.never()).findByPlate("");
    }
}
