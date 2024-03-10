package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightServiceRepository extends JpaRepository<FlightService, Long> {

}
