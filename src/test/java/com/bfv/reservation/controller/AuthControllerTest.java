package com.bfv.reservation.controller;

import com.bfv.reservation.model.request.user.AuthRequest;
import com.bfv.reservation.model.response.domain.AuthResponse;
import com.bfv.reservation.security.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AuthService service;

    @InjectMocks
    private AuthController ctrl;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ctrl)
                .build();
    }

    @Test
    void shouldAuthOk() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
                .email("test_user-repository_find-by-email@icewize.fr")
                .password("password")
                .build();

        Mockito.when(this.service.auth(Mockito.any())).thenReturn(
                AuthResponse.builder()
                        .message("Successfully authenticated!")
                        .token("the.token")
                        .build()
        );

        // when
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.json(request))
                )


                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Successfully authenticated!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("the.token"));

        Mockito.verify(this.service).auth(Mockito.any());
    }

    @Test
    void shouldAuthFail() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
                .email("localuser")
                .password("123456$")
                .build();

        Mockito.when(this.service.auth(Mockito.any())).thenReturn(
                AuthResponse.builder()
                        .token(null)
                        .build()
        );

        // when
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.json(request))
                )

                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").isEmpty());

        Mockito.verify(this.service).auth(Mockito.any());
    }

    @Test
    void shouldAuthBadRequest() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
                .email("localuser")
                .password("")
                .build();

        // when
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.json(request))
                )

                // then
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.service, Mockito.never()).auth(Mockito.any());
    }

    @Test
    void shouldAuthBadRequestWhenNoRequestBody() throws Exception {
        // given

        // when
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/login"))

                // then
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        Mockito.verify(this.service, Mockito.never()).auth(Mockito.any());
    }

    private String json(AuthRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
