package com.lrodriguez.campsite.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reservation {

    @Id
    private Long id;

    private User user;

    private LocalDateTime arrivalDate;

    private LocalDateTime departureDate;

    private List<Transaction> transactions;

}
