package com.lrodriguez.campsite.controllers;

import com.lrodriguez.campsite.Mappers.AvailabilityMapper;
import com.lrodriguez.campsite.dtos.AvailabilityResponse;
import com.lrodriguez.campsite.dtos.ReservationDTO;
import com.lrodriguez.campsite.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

/**
 * TODO
 */
@RestController
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    private final ReservationService reservationService;

    private final AvailabilityMapper availabilityMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, AvailabilityMapper availabilityMapper) {
        this.reservationService = reservationService;
        this.availabilityMapper = availabilityMapper;
    }

    @PostMapping()
    public ResponseEntity<ReservationDTO> book(@Valid @RequestBody ReservationDTO reservation) {
        // TODO

        ReservationDTO reservationCreated = reservationService.create(reservation);
        // MMMMM
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> retrieve(@PathVariable("id") long id) {
        // TODO
        reservationService.findById(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> modify(@PathVariable("id") long id, @Valid @RequestBody ReservationDTO reservation) {
        // TODO
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> cancel(@PathVariable("id") long id) {
        // TODO
        reservationService.cancel(id);
        return ResponseEntity.ok().body(null);
    }

}
