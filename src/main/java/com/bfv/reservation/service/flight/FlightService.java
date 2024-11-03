package com.bfv.reservation.service.flight;

import com.bfv.reservation.model.domain.flight.Flight;
import com.bfv.reservation.repository.flight.FlightRepository;
import com.bfv.reservation.service.GlobalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService extends GlobalService<Flight, FlightRepository> {
    public FlightService(FlightRepository flightRepository) {
        super(flightRepository);
    }

    public Optional<Flight> getFlightByFlightNumber(String flightNumber) {
        return getRepository().findByFlightNumber(flightNumber);
    }

    public List<Flight> getFlightsByDepartureAndArrival(String departure, String arrival) {
        return getRepository().findAllByDepartureAirportNameAndArrivalAirportName(departure, arrival);
    }
}
