package com.bfv.reservation.repository.car;

import com.bfv.reservation.model.domain.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
