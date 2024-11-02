package com.bfv.reservation.service;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.Hotel;
import com.bfv.reservation.repository.HotelRepository;

@Service
public class HotelService extends GlobalService<Hotel, HotelRepository> {

    public HotelService(HotelRepository repository) {
        super(repository);
    }
}
