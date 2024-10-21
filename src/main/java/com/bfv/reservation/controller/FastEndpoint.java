package com.bfv.reservation.controller;

import com.bfv.reservation.dto.AirlineDTO;
import com.bfv.reservation.dto.DTO;
import com.bfv.reservation.model.airport.Aircraft;
import com.bfv.reservation.model.airport.Airline;
import com.bfv.reservation.repository.airport.AircraftModelRepository;
import com.bfv.reservation.repository.airport.AirlineRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
public class FastEndpoint {
    private final AircraftModelRepository aircraftModelRepository;
    private final AirlineRepository airlineRepository;

    @GetMapping("/")
    public String start() {
        try {
            action();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "Done";
    }

    private void removeDuplicates() {
        List<Aircraft> list = aircraftModelRepository.findAll();
        List<String> listName = new ArrayList<>();

        List<Aircraft> deleteList = new ArrayList<>();

        for (Aircraft object : list) {
            if (!listName.contains(object.getName())) {
                listName.add(object.getName());
            } else {
                deleteList.add(object);
            }
        }

        aircraftModelRepository.deleteAll(deleteList);
    }

    private void action() throws FileNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", "aaf72e42ddmsh4b096706717bc55p1afde8jsn8da65a07c81e");
        headers.set("x-rapidapi-host", "flight-radar1.p.rapidapi.com");
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        //ResponseEntity<DTO> dto = restTemplate.exchange("https://flight-radar1.p.rapidapi.com/aircrafts/list", HttpMethod.GET, entity, DTO.class);



//        if (dto.getBody() != null) {
//            for (AirlineDTO airportDTO : dto.getBody().getRows()) {
//                Airline airline = new Airline();
//
//                BeanUtils.copyProperties(airportDTO, airline);
//                airline.setAircraft(aircraftList.get(random.nextInt(aircraftList.size())));
//
//                airlineRepository.save(airline);
//            }
//        }

        Gson gson = new Gson();
        Type personListType = new TypeToken<List<AirlineDTO>>() {
        }.getType();

        Random random = new Random();
        List<Aircraft> aircraftList = aircraftModelRepository.findAll();

        // Json array to List of objects
        File file = new File("src/main/resources/aircrafts.json");
        List<AirlineDTO> list = gson.fromJson(new FileReader(file), personListType);

        for (AirlineDTO aircraftDTO : list) {
            Airline aircraft = new Airline();
            aircraft.setName(aircraftDTO.getName());
            aircraft.setCode(aircraftDTO.getCode().isBlank() ? null : aircraftDTO.getCode());
            aircraft.setIcao(aircraftDTO.getIcao().isBlank() ? null : aircraftDTO.getIcao());

            aircraft.setAircraft(aircraftList.get(random.nextInt(aircraftList.size())));

            airlineRepository.save(aircraft);
        }
    }
}
