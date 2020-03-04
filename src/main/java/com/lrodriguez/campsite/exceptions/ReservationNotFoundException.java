package com.lrodriguez.campsite.exceptions;

import org.springframework.http.HttpStatus;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException() {
        super(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

}
