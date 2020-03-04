package com.lrodriguez.campsite.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CampsiteUtils {

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return IntStream.iterate(0, i -> i + 1)
            .limit(numOfDaysBetween)
            .mapToObj(startDate::plusDays)
            .collect(Collectors.toList());
    }

}
