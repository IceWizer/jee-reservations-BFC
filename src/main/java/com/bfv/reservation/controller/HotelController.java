package com.bfv.reservation.controller;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.hotel.Hotel;
import com.bfv.reservation.model.domain.hotel.Room;
import com.bfv.reservation.model.request.HotelRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.hotel.HotelService;
import com.bfv.reservation.service.hotel.RoomService;
import com.bfv.reservation.service.location.CityService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import static com.bfv.reservation.Library.HOTEL;
import static com.bfv.reservation.Library.generateID;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController extends BuilderResponse<Hotel> {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final CityService cityService;

    @GetMapping
    public ListResponse<Hotel> getHotels() {
        return getListResponse(hotelService.findAll());
    }

    @GetMapping("/id/{id}")
    public DataResponse<Hotel> getHotelById(@PathVariable String id) {
        return getDataResponse(hotelService.findById(id).orElseThrow(() -> new NotFound(HOTEL, id)), HOTEL);
    }

    @PostMapping()
    @RolesAllowed("ADMIN")
    public BasicResponse saveHotel(@Valid @RequestBody HotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setId(generateID());

        return save(new Hotel(), request);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public BasicResponse updateHotel(@PathVariable String id, @Valid @RequestBody HotelRequest request) {
        return save(hotelService.findById(id).orElseThrow(() -> new NotFound(HOTEL, id)), request);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public BasicResponse deleteHotel(@PathVariable String id) {
        return delete(hotelService.delete(id));
    }

    private BasicResponse save(Hotel hotel, HotelRequest request) {
        BeanUtils.copyProperties(request, hotel);

        hotel.setCity(cityService.findById(request.getCityId()).orElseThrow(() -> new NotFound("City", request.getCityId())));
        String hotelId = hotelService.save(hotel);

        if (request.getRooms() != null && !request.getRooms().isEmpty()) {
            request.getRooms().forEach(roomRequest -> {
                Room room = new Room();
                room.setId(generateID());
                room.setHotel(hotel);

                BeanUtils.copyProperties(roomRequest, room);

                roomService.save(room);
            });
        }

        return save(HOTEL, hotelId);
    }
}
