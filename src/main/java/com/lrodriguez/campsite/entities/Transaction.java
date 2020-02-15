package com.lrodriguez.campsite.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    private Long id;

    private String type;

    private LocalDateTime date;

    private Reservation reservation;

}
