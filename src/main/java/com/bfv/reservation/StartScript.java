package com.bfv.reservation;

import com.bfv.reservation.model.domain.Active;
import com.bfv.reservation.model.domain.flight.Aircraft;
import com.bfv.reservation.model.domain.flight.Airline;
import com.bfv.reservation.model.domain.flight.Airport;
import com.bfv.reservation.model.domain.hotel.Hotel;
import com.bfv.reservation.model.domain.hotel.Room;
import com.bfv.reservation.model.domain.location.City;
import com.bfv.reservation.model.domain.location.Country;
import com.bfv.reservation.model.domain.location.Timezone;
import com.bfv.reservation.repository.ActiveRepository;
import com.bfv.reservation.repository.flight.AircraftRepository;
import com.bfv.reservation.repository.flight.AirlineRepository;
import com.bfv.reservation.repository.flight.AirportRepository;
import com.bfv.reservation.repository.flight.FlightRepository;
import com.bfv.reservation.repository.hotel.HotelRepository;
import com.bfv.reservation.repository.hotel.RoomRepository;
import com.bfv.reservation.repository.location.CityRepository;
import com.bfv.reservation.repository.location.CountryRepository;
import com.bfv.reservation.repository.location.TimezoneRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class StartScript {
    //Location
    private final TimezoneRepository timezoneRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    //Flight
    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    //Hotel
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    private final ActiveRepository activeRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        //Active
        Active active = activeRepository.findById(1L).orElse(null);

        if (active == null) {
            active = new Active();
            active.setId(1L);
            active.setDataLoaded(false);
            activeRepository.save(active);
        }

        if (active.isDataLoaded()) return;
        System.out.println("Loading data...");


        //Timezone
        System.out.println("Start loading timezone data...");
        timezone();
        System.out.println("Timezone data loaded");

        //Country
        System.out.println("Start loading country data...");
        country();
        System.out.println("Country data loaded");

        //City
        System.out.println("Start loading city data...");
        city();
        System.out.println("City data loaded");


        //Aircraft
        System.out.println("Start loading aircraft data...");
        aircraft();
        System.out.println("Aircraft data loaded");

        //Airline
        System.out.println("Start loading airline data...");
        airline();
        System.out.println("Airline data loaded");

        //Airport
        System.out.println("Start loading airport data...");
        airport();
        System.out.println("Airport data loaded");

        //Flight
        System.out.println("Start loading flight data...");
        flight();
        System.out.println("Flight data loaded");


        //Room
        System.out.println("Start loading room data...");
        room();
        System.out.println("Room data loaded");

        //Hotel
        System.out.println("Start loading hotel data...");
        hotel();
        System.out.println("Hotel data loaded");


        //Car
        System.out.println("Start loading car data...");
        car();
        System.out.println("Car data loaded");

        active.setDataLoaded(true);
        activeRepository.save(active);

        System.out.println("Data loaded");
    }

    //Location
    private void timezone() {
        String[] HEADERS = {"id", "name", "offset"};
        List<Timezone> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/location/timezone.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Timezone timezone = new Timezone();
                timezone.setId(csvRecord.get(HEADERS[0]));
                timezone.setName(csvRecord.get(HEADERS[1]));
                timezone.setOffset(Integer.parseInt(csvRecord.get(HEADERS[2])));

                list.add(timezone);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        timezoneRepository.saveAll(list);
    }

    private void country() {
        String[] HEADERS = {"id", "name"};
        List<Country> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/location/country.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Country country = new Country();
                country.setId(csvRecord.get(HEADERS[0]));
                country.setName(csvRecord.get(HEADERS[1]));

                list.add(country);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        countryRepository.saveAll(list);
    }

    private void city() {
        String[] HEADERS = {"id", "name", "country_id", "timezone_id"};
        List<City> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/location/city.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                City city = new City();

                city.setId(csvRecord.get(HEADERS[0]));
                city.setName(csvRecord.get(HEADERS[1]));
                city.setCountry(countryRepository.findById(csvRecord.get(HEADERS[2])).orElse(null));
                city.setTimezone(timezoneRepository.findById(csvRecord.get(HEADERS[3])).orElse(null));

                list.add(city);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        cityRepository.saveAll(list);
    }


    //Flight
    private void aircraft() {
        String[] HEADERS = {"id", "name", "code", "capacity"};
        List<Aircraft> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/flight/aircraft.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Aircraft aircraft = new Aircraft();
                Random random = new Random();

                aircraft.setId(csvRecord.get(HEADERS[0]));
                aircraft.setName(csvRecord.get(HEADERS[1]));
                aircraft.setCode(csvRecord.get(HEADERS[2]));
                aircraft.setCapacity(Integer.parseInt(csvRecord.get(HEADERS[3])));

                list.add(aircraft);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        aircraftRepository.saveAll(list);
    }

    private void airline() {
        String[] HEADERS = {"id", "name", "code", "icao", "aircraft_id"};
        List<Airline> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/flight/airline.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Airline airline = new Airline();

                airline.setId(csvRecord.get(HEADERS[0]));
                airline.setName(csvRecord.get(HEADERS[1]));
                airline.setCode(csvRecord.get(HEADERS[2]));
                airline.setIcao(csvRecord.get(HEADERS[2]));
                airline.setAircraft(aircraftRepository.findById(csvRecord.get(HEADERS[4])).orElse(null));

                list.add(airline);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        airlineRepository.saveAll(list);
    }

    private void airport() {
        String[] HEADERS = {"id", "alt", "iata", "icao", "lat", "lon", "name", "size", "city_id"};
        List<Airport> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/flight/airport.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Airport airport = new Airport();

                airport.setId(csvRecord.get(HEADERS[0]));
                airport.setAlt(Integer.parseInt(csvRecord.get(HEADERS[1])));
                airport.setIata(csvRecord.get(HEADERS[2]));
                airport.setIcao(csvRecord.get(HEADERS[3]));
                airport.setLat(Double.parseDouble(csvRecord.get(HEADERS[4])));
                airport.setLon(Double.parseDouble(csvRecord.get(HEADERS[5])));
                airport.setName(csvRecord.get(HEADERS[6]));
                airport.setSize(Integer.parseInt(csvRecord.get(HEADERS[7])));
                airport.setCity(cityRepository.findById(csvRecord.get(HEADERS[8])).orElse(null));

                list.add(airport);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        airportRepository.saveAll(list);
    }

    private void flight() {
        //TODO : Generate some flights
    }


    //Hotel
    private void hotel() {
        String[] HEADERS = {"id", "address", "email", "name", "phone_number", "rating", "website", "city_id"};

        List<Hotel> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/hotel/hotel.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Hotel hotel = new Hotel();

                hotel.setId(csvRecord.get(HEADERS[0]));
                hotel.setAddress(csvRecord.get(HEADERS[1]));
                hotel.setEmail(csvRecord.get(HEADERS[2]));
                hotel.setName(csvRecord.get(HEADERS[3]));
                hotel.setPhoneNumber(csvRecord.get(HEADERS[4]));
                hotel.setRating(Double.parseDouble(csvRecord.get(HEADERS[5])));
                hotel.setWebsite(csvRecord.get(HEADERS[6]));
                hotel.setCity(cityRepository.findById(csvRecord.get(HEADERS[7])).orElse(null));

                list.add(hotel);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        hotelRepository.saveAll(list);
    }

    private void room() {
        String[] HEADERS = {"id", "available", "capacity", "price", "room_number", "hotel_id"};

        List<Room> list = new ArrayList<>();

        InputStream is = getClass().getResourceAsStream("/data/hotel/room.csv");

        if (is == null) {
            throw new RuntimeException("CSV file not found");
        }

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Room room = new Room();

                room.setId(csvRecord.get(HEADERS[0]));
                room.setCapacity(Integer.parseInt(csvRecord.get(HEADERS[1])));
                room.setPrice(Double.parseDouble(csvRecord.get(HEADERS[2])));
                room.setHotel(hotelRepository.findById(csvRecord.get(HEADERS[3])).orElse(null));

                list.add(room);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }

        roomRepository.saveAll(list);
    }


    //Car
    private void car() {
        //TODO : Implement this method
    }
}
