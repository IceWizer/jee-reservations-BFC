package com.bfv.reservation.controller.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;
import com.bfv.reservation.model.request.hotel.RoomTypeReservationCreateRequest;
import com.bfv.reservation.service.UserService;
import com.bfv.reservation.service.hotel.RoomTypeReservationService;
import com.bfv.reservation.service.hotel.RoomTypeService;

@RestController
@RequestMapping("/api/room-type-reservation")
public class RoomTypeReservationController {

    @Autowired
    private RoomTypeReservationService roomTypeReservationService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private UserService userService;

    @GetMapping("/my-reservations")
    public List<RoomTypeReservation> getAllReservations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ArrayList<>();
        }
        String email = auth.getPrincipal().toString();
        User currentUser = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return roomTypeReservationService.findByUser(currentUser);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomTypeReservation> createReservation(@RequestBody RoomTypeReservationCreateRequest reservation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = auth.getPrincipal().toString();
        User currentUser = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (!roomTypeReservationService.isAvailable(reservation.getRoomTypeId(), reservation.getFromDate(), reservation.getToDate())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        RoomTypeReservation createdReservation = reservation.toRoomTypeReservation(this.roomTypeService);
        createdReservation.setUser(currentUser);
        try {
            roomTypeReservationService.save(createdReservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdReservation);
    }

    @PatchMapping("/cancel/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> cancelReservation(@PathVariable String reservationId) {
        try {
            roomTypeReservationService.cancel(reservationId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
