package com.bfv.reservation.model.request.hotel;

import com.bfv.reservation.model.domain.hotel.RoomType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeUpdateRequest {

    private String name;
    private String description;
    private int number;
    private double price;

    public RoomType toRoomType() {
        RoomType roomType = new RoomType();
        roomType.setName(this.name);
        roomType.setDescription(this.description);
        roomType.setNumber(this.number);
        roomType.setPrice(this.price);
        return roomType;
    }
}
