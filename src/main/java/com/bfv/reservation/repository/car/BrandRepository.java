package com.bfv.reservation.repository.car;

import com.bfv.reservation.model.domain.car.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByName(String name);
}
