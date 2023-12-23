package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.service.AirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
@Slf4j
public class AirportController {

    private final AirportService airportService;


    //    TODO: make logging for controllers
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Airport> getAllAirports() {
        return airportService.findAll();
    }

}
