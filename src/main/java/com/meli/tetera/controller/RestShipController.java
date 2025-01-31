package com.meli.tetera.controller;

import com.meli.tetera.exceptions.InvalidStatusException;
import com.meli.tetera.exceptions.NoContentException;
import com.meli.tetera.exceptions.SystemNotFoundException;
import com.meli.tetera.model.System;
import com.meli.tetera.services.ShipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "https://makers-challenge.altscore.ai/v1/s1/e7/")
@Tag(name = "SHIP", description = "All ship methods")
public class RestShipController {

    private final ShipService shipService;

    public RestShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/status")
    @ResponseBody
    @Operation(summary = "Get the status of the ship", description = "In this method you can get the status of the ship")
    @Tag(name = "GET", description = "All get methods")
    public Map<String, String> getStatus() {
        System system = shipService.getStatus();
        return Map.of(system.getName(), system.getCode());

    }

    @PostMapping("/change-status")
    @ResponseBody
    @SecurityRequirement(name = "basicAuth")
    @Operation(summary = "Update the status of the ship", description = "In this method you can update the status of the ship")
    @Tag(name = "POST", description = "All post methods")
    public ResponseEntity<String> updateStatus(@RequestBody Map<String, String> newStatus) {
        return shipService.updateStatus(newStatus);
    }
    @PostMapping("/teapot")
    @Tag(name = "POST", description = "All post methods")
    @Operation(summary = "Make a teapot", description = "In this method you can make a teapot")
    public ResponseEntity<Void> postTeapot() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}