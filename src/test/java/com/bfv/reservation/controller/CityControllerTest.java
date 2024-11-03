package com.bfv.reservation.controller;

import com.bfv.reservation.model.domain.location.City;
import com.bfv.reservation.repository.UserRepository;
import com.bfv.reservation.service.car.CarService;
import com.bfv.reservation.service.location.CityService;
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

@WebMvcTest(CityController.class)
@WithMockUser
@ActiveProfiles("test")
@EnableMethodSecurity()
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService service;

    @MockBean
    @SuppressWarnings("unused")
    private UserRepository userRepository;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cities/"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.service).findAll();
    }

    @Test
    void shouldFindById() throws Exception {
        //Given
        String id = "paris";

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cities/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.service).findById(id);
    }

    @Test
    void shouldNotFind() throws Exception {
        //Given
        String id = "parises";

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cities/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(this.service).findById(id);
    }
}
