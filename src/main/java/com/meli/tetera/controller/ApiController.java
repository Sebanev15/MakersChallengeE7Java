package com.meli.tetera.controller;

import com.meli.tetera.model.System;
import com.meli.tetera.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ApiController {

    private final ShipService shipService;

    public ApiController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/repair-bay")
    public String getRepairBay(Map<String, Object> model) {
        System damagedSystem = shipService.getStatus();
        model.put("anchorPoint", damagedSystem.getCode());
        return "repair";
    }
}