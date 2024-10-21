package com.bfv.reservation.repository;

import com.bfv.reservation.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
