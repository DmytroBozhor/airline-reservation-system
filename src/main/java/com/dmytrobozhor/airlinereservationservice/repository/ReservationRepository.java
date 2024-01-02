package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(nativeQuery = true,
            value = "select * from reservation " +
                    "where passenger_id = :#{#reservation.getPassenger().getId()} " +
                    "and seat_details_id = :#{#reservation.getSeatDetail().getId()} " +
                    "and reservation_date_time = :#{#reservation.getReservationDateTime()}")
    Optional<Reservation> findByAllFields(Reservation reservation);

}
