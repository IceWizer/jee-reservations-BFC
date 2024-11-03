package com.bfv.reservation.service;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.Car;
import com.bfv.reservation.repository.CarRepository;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService extends GlobalService<Car, CarRepository> {
    public CarService(CarRepository repository) {
        super(repository);
    }

    public Optional<Car> getCarByPlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            throw new NotFound(plate != null ? plate : "", "Car");
        }
        return Optional.of(getRepository().findByPlate(plate).orElseThrow(() -> new NotFound(plate, "Car")));
    }
}
