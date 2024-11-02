package com.bfv.reservation.controller;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.Car;
import com.bfv.reservation.model.request.CarRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController extends BuilderResponse<Car> {

    private final static String CAR = "Car";

    private final CarService carService;

    @GetMapping("/")
    public ListResponse<Car> getCars() {
        return getListResponse(carService.findAll());
    }


    @GetMapping("/id/{id}")
    public DataResponse<Car> getCarById(@PathVariable String id) {
        return getDataResponse(carService.findById(id).orElseThrow(() -> new NotFound(CAR, id)), CAR);
    }

    @GetMapping("/plate/{plate}")
    public DataResponse<Car> getCarByPlate(@PathVariable String plate) {
        return getDataResponse(carService.getCarByPlate(plate).orElseThrow(() -> new NotFound(CAR, plate)), CAR);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse createCar(@Valid CarRequest carRequest) {
        return save(new Car(), carRequest);
    }

    @PutMapping("/{id}")
    public BasicResponse updateCar(@PathVariable String id, @Valid CarRequest carRequest) {
        return save(carService.findById(id).orElseThrow(() -> new NotFound(CAR, id)), carRequest);
    }

    @DeleteMapping("/{id}")
    public BasicResponse deleteCar(@PathVariable String id) {
        return delete(carService.delete(id));
    }

    private BasicResponse save(Car car, CarRequest carRequest) {
        BeanUtils.copyProperties(carRequest, car);

        return save(carService.save(car));
    }
}
