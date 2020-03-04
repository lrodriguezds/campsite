package com.lrodriguez.campsite.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health controller contains one single endpoint that retrieves OK if API is up and running.
 */
@RestController
@RequestMapping(value = "/api/health", produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthController {

    private static ResponseEntity<String> response = ResponseEntity.ok("{\"status\":\"Ok\"}");

    @GetMapping()
    public ResponseEntity<String> health() {
        return response;
    }

}
