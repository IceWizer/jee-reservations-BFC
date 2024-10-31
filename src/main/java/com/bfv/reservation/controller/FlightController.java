package com.bfv.reservation.controller;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.flight.Flight;
import com.bfv.reservation.model.request.FlightRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.FlightService;
import com.bfv.reservation.service.airport.AirlineService;
import com.bfv.reservation.service.airport.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController extends BuilderResponse<Flight> {
    private final static String FLIGHT = "Flight";
    private final static String AIRPORT = "Airport";

    private final AirportService airportService;
    private final AirlineService airlineService;
    private final FlightService flightService;

    @GetMapping
    public ListResponse<Flight> getFlights() {
        return getListResponse(flightService.findAll());
    }

    @GetMapping("/id/{id}")
    public DataResponse<Flight> getFlightById(@PathVariable String id) {
        return getDataResponse(flightService.findById(id).orElseThrow(() -> new NotFound(FLIGHT, id)), FLIGHT);
    }

    @GetMapping("/flightNumber/{flightNumber}")
    public DataResponse<Flight> getFlightByFlightNumber(@PathVariable String flightNumber) {
        return getDataResponse(flightService.getFlightByFlightNumber(flightNumber).orElseThrow(() -> new NotFound(FLIGHT, flightNumber)), FLIGHT);
    }

    //TODO : We need to use a query model to get the flights by departure
//    @GetMapping("/departure/{departure}")
//    public ListResponse<Flight> getFlightsByDeparture(@PathVariable String departure) {
//        return ListResponse.<Flight>builder()
//                .count(getService().getFlightsByDeparture(departure).size())
//                .data(getService().getFlightsByDeparture(departure))
//                .build();
//    }

    @PostMapping("/")
    public BasicResponse saveFlight(@Valid FlightRequest flightRequest) {
        return save(new Flight(), flightRequest);
    }

    @PutMapping("/{id}")
    public BasicResponse updateFlight(@PathVariable String id, @Valid FlightRequest flightRequest) {
        return save(flightService.findById(id).orElseThrow(() -> new NotFound(FLIGHT, id)), flightRequest);
    }

    @DeleteMapping("/{id}")
    public BasicResponse deleteFlight(@PathVariable String id) {
        return delete(flightService.delete(id));
    }

    //Copy and save all the properties from the request to the flight object
    private BasicResponse save(Flight flight, FlightRequest flightRequest) {
        BeanUtils.copyProperties(flightRequest, flight);

        //Airport
        flight.setDepartureAirport(airportService.getAirportByIata(flightRequest.getDepartureAirportIATA()).orElseThrow(() -> new NotFound(AIRPORT, flightRequest.getDepartureAirportIATA())));
        flight.setArrivalAirport(airportService.getAirportByIata(flightRequest.getArrivalAirportIATA()).orElseThrow(() -> new NotFound(AIRPORT, flightRequest.getArrivalAirportIATA())));

        //Airline
        flight.setAirline(airlineService.getAirlineByIcao(flightRequest.getAirlineICAO()).orElseThrow(() -> new NotFound("Airline", flightRequest.getAirlineICAO())));

        flight.setDepartureTime(flightRequest.getDepartureTime());
        flight.setDepartureTime(flightRequest.getArrivalTime());

        //Calculate duration
        flight.setDuration(ChronoUnit.MINUTES.between(flightRequest.getDepartureTime(), flightRequest.getArrivalTime()));

        return save(flightService.save(flight));
    }
}
