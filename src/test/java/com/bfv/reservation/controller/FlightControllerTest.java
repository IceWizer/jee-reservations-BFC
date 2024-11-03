package com.bfv.reservation.controller;

import static com.bfv.reservation.Library.FLIGHT;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bfv.reservation.model.domain.flight.Flight;
import com.bfv.reservation.model.request.FlightRequest;
import com.bfv.reservation.service.UserService;
import com.bfv.reservation.service.flight.AirlineService;
import com.bfv.reservation.service.flight.AirportService;
import com.bfv.reservation.service.flight.FlightService;
import com.bfv.reservation.exception.NotFound;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AirportService airportService;

    @MockBean
    private AirlineService airlineService;

    @MockBean
    private FlightService flightService;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        // Given
        List<Flight> flights = List.of(new Flight(), new Flight());
        Mockito.when(flightService.findAll()).thenReturn(flights);

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.flightService).findAll();
    }

    @Test
    void shouldFindFlightsByDepartureAndArrival() throws Exception {
        // Given
        String departure = "CDG";
        String arrival = "JFK";
        List<Flight> flights = List.of(new Flight(), new Flight());
        Mockito.when(flightService.getFlightsByDepartureAndArrival(departure, arrival)).thenReturn(flights);

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/by-departure-arrival")
                .param("departure", departure)
                .param("arrival", arrival))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        Mockito.verify(this.flightService).getFlightsByDepartureAndArrival(departure, arrival);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    @Test
    void shouldNotFindFlightsByDepartureAndArrival() throws Exception {
        // Given
        String departure = "CDG";
        Mockito.when(flightService.getFlightsByDepartureAndArrival(departure, null)).thenReturn(List.of());

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/by-departure-arrival?departure=CDG"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldFindFlightById() throws Exception {
        // Given
        String id = "flight-id";
        Flight flight = new Flight();  // Create a mock Flight object and set fields as needed
        Mockito.when(flightService.findById(id)).thenReturn(java.util.Optional.of(flight));

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/id/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        Mockito.verify(this.flightService).findById(id);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    @Test
    void shouldNotFindFlightById() throws Exception {
        // Given
        String id = "flight-id";
        Mockito.when(flightService.findById(id)).thenReturn(java.util.Optional.empty());

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/id/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Then
        Mockito.verify(this.flightService).findById(id);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    @Test
    void shouldFindFlightByFlightNumber() throws Exception {
        // Given
        String flightNumber = "AF123";
        Flight flight = new Flight();  // Create a mock Flight object and set fields as needed
        Mockito.when(flightService.getFlightByFlightNumber(flightNumber)).thenReturn(java.util.Optional.of(flight));

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/flightNumber/" + flightNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        Mockito.verify(this.flightService).getFlightByFlightNumber(flightNumber);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    @Test
    void shouldNotFindFlightByFlightNumber() throws Exception {
        // Given
        String flightNumber = "AF123";
        Mockito.when(flightService.getFlightByFlightNumber(flightNumber)).thenReturn(java.util.Optional.empty());

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/flightNumber/" + flightNumber))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Then
        Mockito.verify(this.flightService).getFlightByFlightNumber(flightNumber);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteFlight() throws Exception {
        // Given
        String id = "flight-id";
        Mockito.when(flightService.delete(id)).thenReturn("flight-id");

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Then
        Mockito.verify(this.flightService).delete(id);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldNotDeleteFlight() throws Exception {
        // Given
        String id = "flight-id";
        Mockito.when(flightService.delete(id)).thenThrow(new NotFound(FLIGHT, id));

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Then
        Mockito.verify(this.flightService).delete(id);
        Mockito.verifyNoMoreInteractions(this.flightService);
    }

    private String json(Object o) throws Exception {
        return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(o);
    }
}
