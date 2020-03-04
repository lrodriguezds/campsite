package com.lrodriguez.campsite.Mappers;

import com.lrodriguez.campsite.dtos.AvailabilityResponse;
import com.lrodriguez.campsite.dtos.DateAvailabilityDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class AvailabilityMapper {

    /**
     * TODO
     * @param dates
     * @return
     */
    public AvailabilityResponse toAvailabilityResponse(Map<LocalDate, Boolean> dates) {
        AvailabilityResponse availabilityResponse = new AvailabilityResponse();
        dates.forEach((date, isAvailable) -> availabilityResponse.getDates().add(new DateAvailabilityDTO(date, isAvailable)));

        return availabilityResponse;
    }

}

