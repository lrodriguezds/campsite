package com.lrodriguez.campsite.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health controller contains one single endpoint that retrieves OK if API is up and running.
 */
@RestController
public class HealthController {

    private static ResponseEntity<String> response = ResponseEntity.ok("{\"status\":\"Ok\"}");

    @GetMapping(path = "/health")
    public ResponseEntity<String> health() {
        return response;
    }

}
