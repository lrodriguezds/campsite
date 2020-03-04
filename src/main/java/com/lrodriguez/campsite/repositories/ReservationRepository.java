package com.lrodriguez.campsite.repositories;

import com.lrodriguez.campsite.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Fin active reservations given date range.
     *
     * @param startDate
     * @param endDate
     * @return List<Reservation>
     */
    @Query("SELECT r FROM Reservation r " +
        "WHERE r.status = 0 AND :endDate > r.checkIn AND r.checkOut > :startDate")
    List<Reservation> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    /**
     * Find active bookings for the given date range.
     *
     * @param startDate range start date
     * @param endDate range end date
     * @return list of active bookings for the given date range
     */
    /*@Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="100")})
    @Query("select r from Reservation r "
        + "where ((b.startDate < ?1 and ?2 < b.endDate) "
        + "or (?1 < b.endDate and b.endDate <= ?2) "
        + "or (?1 <= b.startDate and b.startDate <=?2)) "
        + "and b.active = true "
        + "order by b.startDate asc")
    List<Reservation> findForDateRange(LocalDate startDate, LocalDate endDate);*/

}
