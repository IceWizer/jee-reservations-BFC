package com.bfv.reservation.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest(CarController.class)
@WithMockUser
@ActiveProfiles("test")
@EnableMethodSecurity()
public class HotelControllerTest {

}
