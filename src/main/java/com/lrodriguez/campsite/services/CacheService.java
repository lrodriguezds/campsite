package com.lrodriguez.campsite.services;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

/**
 * TODO:
 */
@Service
public class CacheService {

    private CacheManager cacheManager;

    private Cache availabilityCache;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void init() {
        availabilityCache = cacheManager.getCache("availability");
        if (availabilityCache == null) {
            throw new IllegalStateException("Cannot find availability cache");
        }
    }

    public void putAvailableDateInCache(LocalDate date, boolean isAvailable) {
        availabilityCache.put(date, isAvailable);
    }

    public Boolean getAvailabilityFromCache(LocalDate date) {
        Cache.ValueWrapper valueWrapper = availabilityCache.get(date);
        return valueWrapper != null ? (Boolean) valueWrapper.get() : null;
    }

}
