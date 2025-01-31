package com.meli.tetera.services;

import com.meli.tetera.exceptions.InvalidStatusException;
import com.meli.tetera.exceptions.NoContentException;
import com.meli.tetera.exceptions.SystemNotFoundException;
import com.meli.tetera.model.System;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ShipService {

    private static final List<System> SYSTEMS = List.of(
            new System("navigation", "NAV-01"),
            new System("communications", "COM-02"),
            new System("life_support", "LIFE-03"),
            new System("engines", "ENG-04"),
            new System("deflector_shield", "SHLD-05")
    );

    public System getStatus() {
        if (SYSTEMS.isEmpty()) {
            throw new NoContentException("The systems are empty");
        }
        return SYSTEMS.get(new Random().nextInt(SYSTEMS.size()));
    }

    public ResponseEntity<String> updateStatus(Map<String, String> newStatus) {
        if (!newStatus.containsKey("damaged_system") || !newStatus.containsKey("status")) {
            throw new InvalidStatusException("The status is invalid");
        }
        if (SYSTEMS.stream().noneMatch(system -> system.getName().equals(newStatus.get("damaged_system")))) {
            throw new SystemNotFoundException("The system was not found");
        }
        return ResponseEntity.ok("Status updated successfully");
    }
}