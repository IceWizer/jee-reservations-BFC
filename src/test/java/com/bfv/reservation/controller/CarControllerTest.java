package com.bfv.reservation.controller;

import com.bfv.reservation.model.request.CarRequest;
import com.bfv.reservation.repository.UserRepository;
import com.bfv.reservation.service.car.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CarController.class)
@WithMockUser
@ActiveProfiles("test")
@EnableMethodSecurity()
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService service;

    @MockBean
    @SuppressWarnings("unused")
    private UserRepository userRepository;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.service).findAll();
    }

    @Test
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

        Mockito.verify(this.service, Mockito.never()).save(Mockito.any());
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
                                .content(carRequestJson)
                )

                // then
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(this.service).save(Mockito.any());
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
