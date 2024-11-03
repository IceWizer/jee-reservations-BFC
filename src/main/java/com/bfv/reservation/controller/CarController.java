package com.bfv.reservation.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.bfv.reservation.Library.CAR;
import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.car.Car;
import com.bfv.reservation.model.request.CarRequest;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.car.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController extends BuilderResponse<Car> {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public ListResponse<Car> getCars() {
        return getListResponse(carService.findAll().stream().filter(Car::isAvailable).toList());
    }

    @GetMapping("/id/{id}")
    public DataResponse<Car> getCarById(@PathVariable String id) {
        return getDataResponse(carService.findById(id).orElseThrow(() -> new NotFound(CAR, id)), CAR);
    }

    @GetMapping("/plate/{plate}")
    public DataResponse<Car> getCarByPlate(@PathVariable String plate) {
        return getDataResponse(carService.getCarByPlate(plate).orElseThrow(() -> new NotFound(CAR, plate)), CAR);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse createCar(@Valid @RequestBody CarRequest carRequest) {
        return save(new Car(), carRequest);
    }

    @PutMapping("/save/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BasicResponse updateCar(@PathVariable String id, @Valid @RequestBody CarRequest carRequest) {
        return save(carService.findById(id).orElseThrow(() -> new NotFound(CAR, id)), carRequest);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BasicResponse deleteCar(@PathVariable String id) {
        return delete(carService.delete(id));
    }

    private BasicResponse save(Car car, CarRequest carRequest) {
        BeanUtils.copyProperties(carRequest, car);

        return save(CAR, carService.save(car));
    }
}
