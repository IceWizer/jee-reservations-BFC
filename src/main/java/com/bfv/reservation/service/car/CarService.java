package com.bfv.reservation.service.car;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.car.Car;
import com.bfv.reservation.repository.car.CarRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class CarService extends GlobalService<Car, CarRepository> {

    public CarService(CarRepository repository) {
        super(repository);
    }

    public Optional<Car> getCarByPlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            throw new NotFound(plate != null ? plate : "", "Car");
        }
        return getRepository().findByPlate(plate);
    }
}
