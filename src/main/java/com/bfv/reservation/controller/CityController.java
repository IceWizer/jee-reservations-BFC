package com.bfv.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bfv.reservation.Library.CITY;
import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.location.City;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.location.CityService;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController extends BuilderResponse<City> {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ListResponse<City> getCities() {
        return getListResponse(cityService.findAll());
    }

    @GetMapping("/{id}")
    public DataResponse<City> getCityById(@PathVariable String id) {
        return getDataResponse(cityService.findById(id).orElseThrow(() -> new NotFound(CITY, id)), CITY);
    }

    @GetMapping("/name/{name}")
    public DataResponse<City> getCityByName(@PathVariable String name) {
        return getDataResponse(cityService.findByName(name).orElseThrow(() -> new NotFound(CITY, name)), CITY);
    }
}
