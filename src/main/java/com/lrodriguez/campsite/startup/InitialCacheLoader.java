package com.lrodriguez.campsite.startup;

import com.lrodriguez.campsite.repositories.ReservationRepository;
import com.lrodriguez.campsite.services.CacheService;
import com.lrodriguez.campsite.utils.CampsiteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Lifecycle implementation that loads elements into the cache at start time, before starting to receive requests.
 */
@Component
public class InitialCacheLoader {

    private CacheService cacheService;

    private ReservationRepository reservationRepository;

    private static final Logger logger = LoggerFactory.getLogger(InitialCacheLoader.class);

    @Autowired
    public InitialCacheLoader(CacheService cacheService, ReservationRepository reservationRepository) {
        this.cacheService = cacheService;
        this.reservationRepository = reservationRepository;
    }

    @EventListener
    public void onStartUp() {
        logger.info("Starting cache loading, looking for availabilty for 1 year range");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate nextYear = tomorrow.plusYears(1);

        // TODO:
        reservationRepository.getAvailabilityForDate(CampsiteUtils.getDatesBetween(tomorrow, nextYear));
        cacheService.putAvailableDateInCache();
    }

}
