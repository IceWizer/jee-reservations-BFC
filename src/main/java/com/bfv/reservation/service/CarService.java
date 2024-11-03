package com.bfv.reservation.service;

import org.springframework.stereotype.Service;

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
        return getRepository().findByPlate(plate);
    }
}
