package com.bfv.reservation.repository.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfv.reservation.model.domain.hotel.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {

    List<RoomType> findByHotelId(String hotelId);
}
