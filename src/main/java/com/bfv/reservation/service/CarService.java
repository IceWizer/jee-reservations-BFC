package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.Car;
import com.bfv.reservation.repository.CarRepository;

public class CarService extends GlobalService<Car, CarRepository> {
    public CarService(CarRepository repository) {
        super(repository);
    }
}
