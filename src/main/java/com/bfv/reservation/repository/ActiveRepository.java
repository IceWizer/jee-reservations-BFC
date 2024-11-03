package com.bfv.reservation.repository;

import com.bfv.reservation.model.domain.Active;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveRepository extends JpaRepository<Active, Long> {
}
