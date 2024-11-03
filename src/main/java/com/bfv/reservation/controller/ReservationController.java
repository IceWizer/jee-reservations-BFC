package com.bfv.reservation.controller;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.Reservation;
import com.bfv.reservation.model.request.reservation.ReservationCarRequest;
import com.bfv.reservation.model.request.reservation.ReservationRequest;
import com.bfv.reservation.model.request.reservation.ReservationRoomRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.ReservationService;
import com.bfv.reservation.service.UserService;
import com.bfv.reservation.service.car.CarService;
import com.bfv.reservation.service.flight.FlightService;
import com.bfv.reservation.service.hotel.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.bfv.reservation.Library.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController extends BuilderResponse<Reservation> {
    private final FlightService flightService;
    private final ReservationService reservationService;
    private final UserService userService;
    private final CarService carService;
    private final RoomService roomService;

    @GetMapping
    public ListResponse<Reservation> getReservations() {
        return getListResponse(reservationService.findAll());
    }

    @GetMapping("/id/{id}")
    public DataResponse<Reservation> getReservationById(@PathVariable String id) {
        return getDataResponse(reservationService.findById(id).orElseThrow(() -> new NotFound(RESERVATION, id)), RESERVATION);
    }

    @GetMapping("/reservationNumber/{reservationNumber}")
    public DataResponse<Reservation> getReservationByReservationNumber(@PathVariable String reservationNumber) {
        return getDataResponse(reservationService.getReservationByReservationNumber(reservationNumber).orElseThrow(() -> new NotFound(RESERVATION, reservationNumber)), RESERVATION);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse saveReservation(@Valid @RequestBody ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setId(generateID());

        return save(new Reservation(), request);
    }

    @PutMapping("/save/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BasicResponse updateReservation(@PathVariable String id, @Valid @RequestBody ReservationRequest request) {
        return save(reservationService.findById(id).orElseThrow(() -> new NotFound(RESERVATION, id)), request);
    }

    @PutMapping("/save/car/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BasicResponse saveReservationCar(@PathVariable String id, @Valid @RequestBody ReservationCarRequest request) {
        Reservation reservation = reservationService.findById(id).orElseThrow(() -> new NotFound(RESERVATION, id));
        reservation.setCar(carService.findById(request.getCarId()).orElseThrow(() -> new NotFound(CAR, request.getCarId())));

        return save(RESERVATION, reservationService.save(reservation));
    }

    @PutMapping("/save/room/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BasicResponse saveReservationRoom(@PathVariable String id, @Valid @RequestBody ReservationRoomRequest request) {
        Reservation reservation = reservationService.findById(id).orElseThrow(() -> new NotFound(RESERVATION, id));
        reservation.setRoom(roomService.findById(request.getRoomId()).orElseThrow(() -> new NotFound(CAR, request.getRoomId())));

        return save(RESERVATION, reservationService.save(reservation));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BasicResponse deleteReservation(@PathVariable String id) {
        return delete(reservationService.delete(id));
    }

    private BasicResponse save(Reservation reservation, ReservationRequest request) {
        BeanUtils.copyProperties(request, reservation);

        reservation.setDepartureFlight(flightService.getFlightByFlightNumber(request.getDepartureFlightNumber()).orElseThrow(() -> new NotFound(FLIGHT, request.getDepartureFlightNumber())));
        reservation.setReturnFlight(flightService.getFlightByFlightNumber(request.getReturnFlightNumber()).orElseThrow(() -> new NotFound(FLIGHT, request.getReturnFlightNumber())));

        reservation.setUser(userService.findById(request.getUserId()).orElseThrow(() -> new NotFound(USER, request.getUserId())));

        reservation.setTotalPrice(reservation.getDepartureFlight().getPrice() + reservation.getReturnFlight().getPrice());

        return save(RESERVATION, reservationService.save(reservation));
    }
}
