package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
