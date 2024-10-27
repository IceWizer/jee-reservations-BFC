package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.Hotel;
import com.bfv.reservation.repository.HotelRepository;

public class HotelService extends GlobalService<Hotel, HotelRepository> {
    public HotelService(HotelRepository repository) {
        super(repository);
    }
}
