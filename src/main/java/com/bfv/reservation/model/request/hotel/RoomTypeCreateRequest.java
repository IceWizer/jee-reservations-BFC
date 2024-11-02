package com.bfv.reservation.model.request.hotel;

import org.springframework.beans.factory.annotation.Autowired;

import com.bfv.reservation.model.domain.hotel.RoomType;
import com.bfv.reservation.service.HotelService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeCreateRequest {

    private String name;
    private String description;
    private int number;
    private double price;
    private String hotelId;

    public RoomType toRoomType(HotelService hotelService) {
        RoomType roomType = new RoomType();
        roomType.setName(this.name);
        roomType.setDescription(this.description);
        roomType.setNumber(this.number);
        roomType.setPrice(this.price);
        hotelService.findById(this.hotelId).ifPresent(roomType::setHotel);
        return roomType;
    }
}
