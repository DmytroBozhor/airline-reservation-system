package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;

import java.util.Optional;

public interface AbstractAirportService extends AbstractCrudService<Airport, Integer> {

    Optional<Airport> findByAllFields(Airport airport);

}
