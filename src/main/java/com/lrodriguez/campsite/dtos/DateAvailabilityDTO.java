package com.lrodriguez.campsite.dtos;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DateAvailabilityDTO {

    private LocalDate date;

    private boolean isAvailable;

}
