package com.bfv.reservation.model.request;

import org.springframework.beans.factory.annotation.Autowired;

import com.bfv.reservation.model.domain.Hotel;
import com.bfv.reservation.service.location.CityService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelCreateRequest {

    private String name;
    private String address;
    private String cityId;
    private String phoneNumber;
    private String email;
    private String website;
    private String description;

    public Hotel toHotel(CityService cityService) {
        Hotel hotel = new Hotel();
        hotel.setName(this.name);
        hotel.setAddress(this.address);
        cityService.findById(this.cityId).ifPresent(hotel::setCity);
        hotel.setPhoneNumber(this.phoneNumber);
        hotel.setEmail(this.email);
        hotel.setWebsite(this.website);
        hotel.setDescription(this.description);
        return hotel;
    }
}
