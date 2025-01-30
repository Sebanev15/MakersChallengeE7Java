package com.meli.tetera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class ApiController {

    private static final List<Map<String, String>> SYSTEMS = List.of(
            Map.of("name", "navigation", "code", "NAV-01"),
            Map.of("name", "communications", "code", "COM-02"),
            Map.of("name", "life_support", "code", "LIFE-03"),
            Map.of("name", "engines", "code", "ENG-04"),
            Map.of("name", "deflector_shield", "code", "SHLD-05")
    );

    @GetMapping("/repair-bay")
    public String getRepairBay(Map<String, Object> model) {
        Map<String, String> damagedSystem = SYSTEMS.get(new Random().nextInt(SYSTEMS.size()));
        model.put("anchorPoint", damagedSystem.get("code"));
        return "repair";
    }
}