package com.bfv.reservation.controller;

import com.bfv.reservation.model.request.CarRequest;
import com.bfv.reservation.repository.UserRepository;
import com.bfv.reservation.service.car.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    @SuppressWarnings("unused")
    private UserRepository userRepository;

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.carService).findAll();
    }

    @Test
    @WithMockUser
    void shouldAddStatusForbidden() throws Exception {
        // given
        String carRequestJson = this.json();

        // when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/cars/save")
                        .content(carRequestJson)
        )
                // then
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verify(this.carService, Mockito.never()).save(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAddStatusOk() throws Exception {
        // given
        String carRequestJson = this.json();

        // when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/cars/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carRequestJson)
        ) // then
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(this.carService).save(Mockito.any());
    }

    private String json() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(createCarRequest());
    }

    private CarRequest createCarRequest() {
        return CarRequest.builder()
                .brand("ford")
                .color("red")
                .motor("1.6")
                .plate("1234")
                .build();
    }
}
