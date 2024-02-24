package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;

import java.util.Optional;

public interface AbstractAirportService extends AbstractCrudService<Airport, Long> {

//    Optional<Airport> findByAllFields(Airport airport);

    void deleteAll();
}
