package com.lrodriguez.campsite.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AvailabilityResponse {

    List<DateAvailabilityDTO> dates;

}
