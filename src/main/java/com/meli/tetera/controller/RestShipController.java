package com.meli.tetera.controller;

import com.meli.tetera.exception.NoContentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "https://makers-challenge.altscore.ai/v1/s1/e7/")
@Tag(name = "SHIP", description = "All ship methods")
public class RestShipController {

    private static final List<Map<String, String>> SYSTEMS = List.of(
            Map.of("name", "navigation", "code", "NAV-01"),
            Map.of("name", "communications", "code", "COM-02"),
            Map.of("name", "life_support", "code", "LIFE-03"),
            Map.of("name", "engines", "code", "ENG-04"),
            Map.of("name", "deflector_shield", "code", "SHLD-05")
    );

    @GetMapping("/status")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status of the ship",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(name = "example",
                                    value = "{\"damaged_system\": \"navigation\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Get the status of the ship", description = "In this method you can get the status of the ship")
    @Tag(name = "GET", description = "All get methods")
    public Map<String, String> getStatus() {
        if(SYSTEMS.isEmpty()) {
            throw new NoContentException("The systems are empty");
        }
        Map<String, String> damagedSystem = SYSTEMS.get(new Random().nextInt(SYSTEMS.size()));

        return Map.of("damaged_system", damagedSystem.get("name"));
    }

    @PostMapping("/teapot")
    @Tag(name = "POST", description = "All post methods")
    @Operation(summary = "Make a teapot", description = "In this method you can make a teapot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "418", description = "I'm a teapot")
    })
    public ResponseEntity<Void> postTeapot() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}