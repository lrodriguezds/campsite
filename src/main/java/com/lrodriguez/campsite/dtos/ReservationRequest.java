package com.lrodriguez.campsite.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationRequest {

    private UserDto user;

    private LocalDateTime arrivalDate;

    private LocalDateTime departureDate;

}
