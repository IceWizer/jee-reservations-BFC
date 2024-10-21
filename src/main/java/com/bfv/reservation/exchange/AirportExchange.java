package com.bfv.reservation.exchange;

import com.bfv.reservation.repository.location.CityRepository;
import com.bfv.reservation.repository.location.CountryRepository;
import com.bfv.reservation.repository.location.TimezoneRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AirportExchange {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final TimezoneRepository timezoneRepository;

    public void getAirports() {
        /*

        if (dto.getBody() != null) {
            List<Timezone> timezones = timezoneRepository.findAll();
            List<Country> countries = countryRepository.findAll();
            List<City> cities = cityRepository.findAll();
            List<Airport> airports = airportRepository.findAll();

            //Remove duplicate inside airports list
            for (Airport airport : airports) {
                for (AirportDTO airportDTO : dto.getBody().getRows()) {
                    if (airport.getIata().equals(airportDTO.getIata())) {
                        dto.getBody().getRows().remove(airportDTO);
                        break;
                    }
                }
            }

            for (AirportDTO airport : dto.getBody().getRows()) {
                Airport airportEntity = new Airport();

                BeanUtils.copyProperties(airport, airportEntity);

                //Timezone
                Timezone timezone = timezoneRepository.findByName(airport.getTimezone().getName());
                if (timezone == null) {
                    timezone = new Timezone();
                    BeanUtils.copyProperties(airport.getTimezone(), timezone);

                    timezoneRepository.save(timezone);
                }

                //Country
                Country country = countryRepository.findByName(airport.getCountry());
                if (country == null) {
                    country = new Country();
                    country.setName(airport.getCountry());

                    countryRepository.save(country);
                }

                //City
                City city = cityRepository.findByName(airport.getCity());
                if (city == null) {
                    city = new City();
                    city.setName(airport.getCity());
                    city.setCountry(country);
                    city.setTimezone(timezone);

                    cityRepository.save(city);
                }

                airportEntity.setCity(city);
                airportRepository.save(airportEntity);
            }
        }*/
    }
}
