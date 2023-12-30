package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightDetailRepository extends JpaRepository<FlightDetail, Integer> {

    @Query(nativeQuery = true,
            value = "select * from flight_details " +
                    "where departure_date_time = :#{#flightDetail.getDepartureDateTime()} " +
                    "and arrival_date_time = :#{#flightDetail.getArrivalDateTime()} " +
                    "and airplane_type = :#{#flightDetail.getAirplaneType().toString()} " +
                    "and source_airport_id = :#{#flightDetail.getSourceAirport().getId()} " +
                    "and destination_airport_id = :#{#flightDetail.getDestinationAirport().getId()}")
    Optional<FlightDetail> findByAllFields(FlightDetail flightDetail);

}
