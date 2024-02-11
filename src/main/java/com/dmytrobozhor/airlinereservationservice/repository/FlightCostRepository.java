package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.FlightCost;
import com.dmytrobozhor.airlinereservationservice.domain.compositeid.FlightCostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightCostRepository extends JpaRepository<FlightCost, FlightCostId> {

    @Query(nativeQuery = true,
            value = "select * from flight_cost " +
                    "where seat_details_id = :#{#flightCost.getSeatDetail().getId()} " +
                    "and valid_from_date_id = :#{#flightCost.getValidFromDate().getDate()} " +
                    "and valid_to_date_id = :#{#flightCost.getValidToDate().getDate()} " +
                    "and cost = :#{#flightCost.getCost()}")
    Optional<FlightCost> findByAllFields(FlightCost flightCost);

}
