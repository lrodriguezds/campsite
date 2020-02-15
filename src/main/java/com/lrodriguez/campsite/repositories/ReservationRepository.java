package com.lrodriguez.campsite.repositories;

import com.lrodriguez.campsite.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
