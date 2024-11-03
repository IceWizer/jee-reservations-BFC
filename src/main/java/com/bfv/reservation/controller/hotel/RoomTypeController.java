package com.bfv.reservation.controller.hotel;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.bfv.reservation.model.domain.hotel.RoomType;
import com.bfv.reservation.model.request.hotel.RoomTypeCreateRequest;
import com.bfv.reservation.model.request.hotel.RoomTypeUpdateRequest;
import com.bfv.reservation.service.HotelService;
import com.bfv.reservation.service.hotel.RoomTypeService;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomType>> getRoomTypesByHotelId(@PathVariable String hotelId) {
        List<RoomType> roomTypes = roomTypeService.findByHotelId(hotelId);
        if (roomTypes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roomTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable String id) {
        RoomType roomType = roomTypeService.findById(id).orElse(null);
        if (roomType == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roomType);
    }

    @PostMapping
    public ResponseEntity<RoomType> createRoomType(@RequestBody RoomTypeCreateRequest roomType) {
        RoomType createdRoomType = roomType.toRoomType(hotelService);
        try {
            roomTypeService.save(createdRoomType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdRoomType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomType> updateRoomType(@PathVariable String id, @RequestBody RoomTypeUpdateRequest roomTypeRequest) {
        RoomType existingRoomType = roomTypeService.findById(id).orElse(null);
        if (existingRoomType == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(roomTypeRequest, existingRoomType);

        existingRoomType.setId(id);

        try {
            roomTypeService.save(existingRoomType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(existingRoomType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable String id) {
        RoomType existingRoomType = roomTypeService.findById(id).orElse(null);
        if (existingRoomType == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            roomTypeService.delete(existingRoomType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
