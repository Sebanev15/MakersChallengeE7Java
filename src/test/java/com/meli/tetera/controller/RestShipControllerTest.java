package com.meli.tetera.controller;

import com.meli.tetera.config.TestSecurityConfig;
import com.meli.tetera.exceptions.NoContentException;
import com.meli.tetera.model.System;
import com.meli.tetera.services.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestShipController.class)
@Import(TestSecurityConfig.class)
public class RestShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShipService shipService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStatus() throws Exception {
        System damagedSystem = new System("navigation", "NAV-01");
        when(shipService.getStatus()).thenReturn(damagedSystem);

        mockMvc.perform(get("/status"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"navigation\",\"code\":\"NAV-01\"}"));
    }

    @Test
    public void testGetStatusNoContent() throws Exception {
        when(shipService.getStatus()).thenThrow(new NoContentException("The systems are empty"));

        mockMvc.perform(get("/status"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Map<String, String> newStatus = Map.of("damaged_system", "navigation", "status", "repaired");
        when(shipService.updateStatus(newStatus)).thenReturn(ResponseEntity.ok("Status updated successfully"));

        mockMvc.perform(post("/change-status")
                        .contentType("application/json")
                        .content("{\"damaged_system\": \"navigation\", \"status\": \"repaired\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Status updated successfully"));
    }

    @Test
    public void testPostTeapot() throws Exception {
        mockMvc.perform(post("/teapot"))
                .andExpect(status().isIAmATeapot());
    }
}