package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatDetailRepository extends JpaRepository<SeatDetail, Integer> {

    @Query(nativeQuery = true,
            value = "select * from seat_details " +
                    "where travel_class_id = :#{#seatDetail.getTravelClass().getId()} " +
                    "and flight_details_id = :#{#seatDetail.getFlightDetail().getId() } ")
    Optional<SeatDetail> findByAllFields(SeatDetail seatDetail);

}
