package com.bfv.reservation.repository;

import com.bfv.reservation.model.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, String> {
    Optional<Car> findByPlate(String plate);
}
