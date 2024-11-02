package com.bfv.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfv.reservation.model.domain.Hotel;
import com.bfv.reservation.model.request.HotelCreateRequest;
import com.bfv.reservation.model.request.HotelUpdateRequest;
import com.bfv.reservation.service.HotelService;
import com.bfv.reservation.service.location.CityService;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<Hotel> index() {
        return hotelService.findAll();
    }

    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody HotelCreateRequest hotelRequest) {
        Hotel hotel = hotelRequest.toHotel(this.cityService);
        try {
            hotelService.save(hotel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable String id, @RequestBody HotelUpdateRequest hotelRequest) {
        Hotel hotel = hotelService.findById(id).orElse(null);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }

        Hotel updatedHotel = hotelRequest.toHotel(this.cityService);
        updatedHotel.setId(id);

        try {
            hotelService.save(updatedHotel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Hotel existingHotel = hotelService.findById(id).orElse(null);
        if (existingHotel == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            hotelService.delete(existingHotel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
