package com.lrodriguez.campsite.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private int status;

    public Reservation(String userEmail, String userFullName, LocalDate checkIn, LocalDate checkOut) {
        this.user = new User(userEmail, userFullName);
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = Status.BOOKED.value;
    }

    public Reservation(Long id, String userEmail, String userFullName, LocalDate checkIn, LocalDate checkOut) {
        this.user = new User(userEmail, userFullName);
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public void cancel() {
        this.status = Status.CANCELLED.value;
    }

    private enum Status {

        BOOKED(0),
        CANCELLED(1);

        int value;

        Status(int value) {
            this.value = value;
        }

    }

}
