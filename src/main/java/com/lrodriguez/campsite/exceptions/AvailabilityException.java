package com.lrodriguez.campsite.exceptions;

public class AvailabilityException extends RuntimeException {

    private String message;

    public AvailabilityException(String message) {
        this.message = message;
    }

}
