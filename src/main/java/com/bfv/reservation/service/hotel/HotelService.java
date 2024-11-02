package com.bfv.reservation.service.hotel;

import com.bfv.reservation.model.domain.hotel.Hotel;
import com.bfv.reservation.repository.hotel.HotelRepository;
import com.bfv.reservation.service.GlobalService;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends GlobalService<Hotel, HotelRepository> {
    public HotelService(HotelRepository repository) {
        super(repository);
    }
}
