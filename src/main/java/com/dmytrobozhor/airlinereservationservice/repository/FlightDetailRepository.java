package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FlightDetailRepository extends JpaRepository<FlightDetail, Long> {

//    @Query(nativeQuery = true,
//            value = "SELECT * FROM flight_details " +
//                    "WHERE departure_date_time = :#{#flightDetail.getDepartureDateTime()} " +
//                    "AND arrival_date_time = :#{#flightDetail.getArrivalDateTime()} " +
//                    "AND airplane_type = :#{#flightDetail.getAirplaneType().toString()} " +
//                    "AND source_airport_id = :#{#flightDetail.getSourceAirport().getId()} " +
//                    "AND destination_airport_id = :#{#flightDetail.getDestinationAirport().getId()}")
//    Optional<FlightDetail> findByAllFields(FlightDetail flightDetail);
//    TODO: this method cannot return anything other than boolean or void, so it is better to create EM delete method
//    @Transactional
//    @Query(nativeQuery = true, value = "DELETE FROM flight_details WHERE id = :id RETURNING *")
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    Optional<FlightDetail> deleteByIdAndReturn(Long id);

}
