package com.bfv.reservation.service.hotel;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.hotel.RoomType;
import com.bfv.reservation.repository.hotel.RoomTypeRepository;
import com.bfv.reservation.service.GlobalService;

@Service
public class RoomTypeService extends GlobalService<RoomType, RoomTypeRepository> {

    public RoomTypeService(RoomTypeRepository repository) {
        super(repository);
    }

    public List<RoomType> findByHotelId(String hotelId) {
        return this.getRepository().findByHotelId(hotelId);
    }
}
