package com.bfv.reservation.controller;

import com.bfv.reservation.model.domain.hotel.Hotel;
import com.bfv.reservation.model.domain.hotel.Room;
import com.bfv.reservation.repository.hotel.HotelRepository;
import com.bfv.reservation.repository.hotel.RoomRepository;
import com.bfv.reservation.service.location.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FastController {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final CityService cityService;

    @GetMapping
    public String action() {
        doAction();

        return "Done";
    }

    private void doAction() {
        //Fill the hotel
        Hotel hotel = new Hotel();
        Room room = new Room();
    }
}