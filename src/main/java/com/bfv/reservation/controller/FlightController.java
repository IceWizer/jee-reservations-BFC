package com.bfv.reservation.controller;

import com.bfv.reservation.exception.ElementNotFound;
import com.bfv.reservation.model.domain.Flight;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController extends GlobalController<Flight, FlightService> {
    public FlightController(FlightService service) {
        super(service);
    }

    @GetMapping
    public ListResponse<Flight> getFlights() {
        return getAll();
    }

    @GetMapping("/id/{id}")
    public DataResponse<Flight> getFlightById(@PathVariable String id) {
        return getOne(id);
    }

    @GetMapping("/flightNumber/{flightNumber}")
    public DataResponse<Flight> getFlightByFlightNumber(@PathVariable String flightNumber) {
        Flight flight = getService().getFlightByFlightNumber(flightNumber).orElseThrow(() -> new ElementNotFound(flightNumber));

        return DataResponse.<Flight>builder()
                .message("Flight found")
                .data(flight)
                .build();
    }
}
