package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightServiceRepository extends JpaRepository<FlightService, Integer> {

    @Query(nativeQuery = true,
            value = "select * from flight_service " +
                    "where service_name = :#{#flightService.getServiceName()} ")
    Optional<FlightService> findByAllFields(FlightService flightService);

}
