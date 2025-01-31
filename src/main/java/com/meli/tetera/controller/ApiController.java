package com.meli.tetera.controller;

import com.meli.tetera.model.System;
import com.meli.tetera.services.ShipService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ApiController {

    @Autowired
    private ShipService shipService;

    @Operation(summary = "Get the status of the ship", description = "In this method you can get the status of the ship")
    @GetMapping("/repair-bay")
    public String getRepairBay(Map<String, Object> model) {
        System damagedSystem = shipService.getStatus();
        model.put("anchorPoint", damagedSystem.getCode());
        return "repair";
    }
}