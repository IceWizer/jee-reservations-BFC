package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.Flight;
import com.bfv.reservation.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService extends GlobalService<Flight, FlightRepository> {
    public FlightService(FlightRepository flightRepository) {
        super(flightRepository);
    }

    //Basic CRUD methods
    public List<Flight> getFlights() {
        return findAll();
    }

    public Optional<Flight> findOne(String id) {
        return findById(id);
    }

    public String saveFlight(Flight flight) {
        return save(flight);
    }

    public String deleteFlight(Flight flight) {
        return delete(flight);
    }


    //Other methods
    public Optional<Flight> getFlightByFlightNumber(String flightNumber) {
        return getRepository().findByFlightNumber(flightNumber);
    }
}
