package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.car.Car;
import com.bfv.reservation.repository.car.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService extends GlobalService<Car, CarRepository> {
    public CarService(CarRepository repository) {
        super(repository);
    }
}
