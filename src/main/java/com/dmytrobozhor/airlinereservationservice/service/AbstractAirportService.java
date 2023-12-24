package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;

import java.util.List;

public interface AbstractAirportService {

    List<Airport> findAll();

}
