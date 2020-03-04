package com.lrodriguez.campsite.services;

import com.lrodriguez.campsite.dtos.DateAvailabilityDTO;
import com.lrodriguez.campsite.dtos.ReservationDTO;
import com.lrodriguez.campsite.dtos.UserDto;
import com.lrodriguez.campsite.entities.Reservation;
import com.lrodriguez.campsite.exceptions.AvailabilityException;
import com.lrodriguez.campsite.exceptions.ReservationNotFoundException;
import com.lrodriguez.campsite.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lrodriguez.campsite.utils.CampsiteUtils.getDatesBetween;

/**
 * TODO
 */
@Service
public class ReservationService {

    private final CacheService cacheService;

    private final ReservationRepository reservationRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    public ReservationService(CacheService cacheService, ReservationRepository reservationRepository) {
        this.cacheService = cacheService;
        this.reservationRepository = reservationRepository;
    }

    public List<DateAvailabilityDTO> getAvailability(LocalDate checkIn, LocalDate checkOut) {
        logger.info("Checking availability for checkIn {} and checkOut {}", checkIn, checkOut);

        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Wrong dates, check in should be greater than check out");
        }

        // TODO: preload the "cache" when the app is starting with 1 year range from now
        Map<LocalDate, Boolean> datesToCheck = getDatesBetween(checkIn, checkOut).stream()
            .collect(Collectors.toMap(date -> date, date -> cacheService.getAvailabilityFromCache(date)));

        // TODO: but if a date is no loaded...

        List<LocalDate> nonDataDates = datesToCheck.entrySet()
            .stream()
            .filter(e -> e.getValue() != null)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        if (nonDataDates.isEmpty()) {
            return datesToCheck;
        }

        /**
         * new way
         */
        List<Reservation> reservations = reservationRepository.findByDateRange(checkIn, checkOut);
        Set<LocalDate> reservedDates = reservations.stream()
            .flatMap(reservation -> getDatesBetween(reservation.getCheckIn(), reservation.getCheckOut()).stream())
            .collect(Collectors.toSet());

        List<LocalDate> datesBetween = getDatesBetween(checkIn, checkOut);

        return datesBetween.stream().map(date -> new DateAvailabilityDTO(date, !reservedDates.contains(date))).collect(Collectors.toList());
    }

    /**
     * Checks for availability and stores a new reservation
     *
     * @param reservationDTO
     * @return Reservation
     */
    public Reservation create(ReservationDTO reservationDTO) {
        logger.info("Creating reservation {}", reservationDTO);
        // TODO: synchronization

        if (!isAvailable(reservationDTO.getCheckIn(), reservationDTO.getCheckOut())) {
            throw new AvailabilityException("There is no availability for the given dates");
        }

        UserDto user = reservationDTO.getUser();
        Reservation reservation = new Reservation(user.getEmail(), user.getFullname(), reservationDTO.getCheckIn(), reservationDTO.getCheckOut());
        return reservationRepository.save(reservation);
    }

    /**
     * Checks availability for a given date range
     *
     * @param startDate
     * @param endDate
     * @return boolean
     */
    private boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.findByDateRange(startDate, endDate);
        return reservations.isEmpty();
    }

    /**
     * Given an id will return a reservation
     *
     * @param id
     * @return Reservation
     */
    public Reservation findById(long id) {
        logger.info("Getting reservation by id {}", id);
        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    /**
     * Given an id, looks for an existing reservation and cancel it. TODO mmmmm
     *
     * @param id
     * @return Reservation
     */
    public Reservation cancel(long id) {
        logger.info("Cancelling reservation by id {}", id);
        Reservation reservation = reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);

        reservation.cancel();
        return reservationRepository.save(reservation);
    }

}
