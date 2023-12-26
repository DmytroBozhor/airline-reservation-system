package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportCreateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
@Slf4j
public class AirportController {

    private final AbstractAirportService airportService;

    private final AirportMapper airportMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Airport> getAllAirports() {
        return airportService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Airport createAirport(@RequestBody @Valid AirportCreateDto airportDto) {
        Airport airport = airportMapper.toAirport(airportDto);
        return airportService.save(airport);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirportById(@PathVariable Integer id) {
        airportService.deleteById(id);
    }

}
