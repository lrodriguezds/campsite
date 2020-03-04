package com.lrodriguez.campsite.controllers;

import com.lrodriguez.campsite.dtos.AvailabilityResponse;
import com.lrodriguez.campsite.dtos.DateAvailabilityDTO;
import com.lrodriguez.campsite.services.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 */
@RestController
@RequestMapping(value = "/api/availabilities", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailabilityController {

    private final ReservationService reservationService;

    public AvailabilityController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(@RequestParam(value = "check-in", required = false) LocalDate checkIn,
                                                                  @RequestParam(value = "check-out", required = false) LocalDate checkOut) {
        if (checkIn == null) {
            checkIn = LocalDate.now().plusDays(1);
        }
        if (checkOut == null) {
            checkOut = checkIn.plusMonths(1);
        }

        List<DateAvailabilityDTO> availability = reservationService.getAvailability(checkIn, checkOut);
        return ResponseEntity.ok().body(new AvailabilityResponse(availability));
    }

}
