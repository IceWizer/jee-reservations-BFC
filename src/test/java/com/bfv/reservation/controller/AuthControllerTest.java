package com.bfv.reservation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bfv.reservation.model.request.user.AuthRequest;
import com.bfv.reservation.security.jwt.JwtHeaderFilter;
import com.bfv.reservation.security.service.AuthService;
import com.bfv.reservation.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthController.class)
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private AuthService authService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtHeaderFilter jwtHeaderFilter;

    @Test
    @WithMockUser
    public void saveUser() {
        AuthRequest request = AuthRequest.builder()
                .email("test2@icewize.fr")
                .password("password")
                .build();

        String json = "{}";
        try {
            json = this.json(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            // when
            this.mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/api/auth/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
            ) // then
                    // .andExpect(MockMvcResultMatchers.status().isCreated())
                    ;

            // Mockito.verify(this.passwordEncoder).encode("password");
            // Mockito.verify(this.userService).save(Mockito.any());
            // Mockito.verify(this.authService).auth(Mockito.any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String json(AuthRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
