package com.bfv.reservation.api;

import com.bfv.reservation.model.domain.Car;
import com.bfv.reservation.model.request.CarRequest;
import com.bfv.reservation.repository.CarRepository;
import com.bfv.reservation.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bfv.reservation.controller.CarController;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CarController.class)
@ActiveProfiles("test")
@WithMockUser
public class CarApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarService carService;

    @Test
    void shouldFindAllStatusOk() throws Exception{
        //given

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/cars/")
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.carService).findAll();
    }

    @Test
    void shouldFindByIdStatusOk() throws Exception{
        //given
        String id = "1";
        Mockito.when(this.carService.findById(id))
            .thenReturn(Optional.of(new Car()));

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/cars/id/" + id)
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.carService).findById(id);
    }

    @Test
    void shouldFindByIdStatusNotFound() throws Exception{
        //given
        String id = "2";

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/cars/id/" + id)
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(this.carService).findById(id);
    }

    @Test
    void shouldFindByPlateStatusOk() throws Exception{
        //given
        String plate = "12-abw-15";

        Mockito.when(this.carService.getCarByPlate(plate))
            .thenReturn(Optional.of(new Car()));

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/cars/plate/" + plate)
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.carService).getCarByPlate(plate);
    }

    @Test
    void shouldFindByPlateStatusNotFound() throws Exception{
        //given
        String plate = "14-zow-72";

        when(carService.getCarByPlate(plate)).thenReturn(Optional.empty());

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/cars/plate/" + plate)
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(this.carService).getCarByPlate(plate);
    }

    @Test
    void shouldCreateStatusCreated() throws Exception{
        //given
        CarRequest carRequest = CarRequest.builder()
            .brand("Toyota")
            .plate("12-abw-15")
            .motor("v8")
            .color("Rouge")
            .build();

        Mockito .when(this.carService.save(Mockito.any()))
            .thenReturn("1");
        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/cars/")
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.json(carRequest))
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isCreated());
        
        Mockito.verify(this.carService).save(Mockito.any());
    }

    @Test
    void shouldCreateStatusFoundBadRequest() throws Exception{
        //given
        CarRequest carRequest = CarRequest.builder()
            .plate("12-abw-15")
            .build();

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/cars/")
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.json(carRequest))
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.carService, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldUpdateStatusOk() throws Exception{
        //given
        String id = "1";
        CarRequest carRequest = CarRequest.builder()
            .brand("Test Model")
            .plate("12-abw-15")
            .color("Rouge")
            .motor("v8")
            .build();

        Mockito.when(this.carService.findById(id))
            .thenReturn(Optional.of(new Car()));

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/cars/" + id)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.json(carRequest))
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.carService).save(Mockito.any());
    }

    @Test
    void shouldUpdateStatusBadRequest() throws Exception{
        //given
        String id = "1";
        CarRequest carRequest = CarRequest.builder()
            .plate("12-abw-15")
            .build();

        Mockito.when(this.carService.findById(id))
            .thenReturn(Optional.of(new Car()));

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/cars/" + id)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.json(carRequest))
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.carService, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldDeleteStatusOk() throws Exception{
        //given
        String id = "1";

        Mockito.when(this.carService.delete(id))
            .thenReturn(id);

        //when
        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/v1/cars/" + id)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
        //then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.carService).delete(id);
    }


    private String json(CarRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }

}
