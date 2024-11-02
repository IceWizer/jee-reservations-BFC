package com.bfv.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfv.reservation.model.domain.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
