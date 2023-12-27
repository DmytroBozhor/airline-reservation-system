package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
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
    public Airport saveAirport(@RequestBody @Valid AirportDto airportDto) {
        var airport = airportMapper.toAirport(airportDto);
        return airportService.save(airport);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirport(@RequestBody @Valid AirportDto airportDto) {
        var airport = airportMapper.toAirport(airportDto);
        airportService.delete(airport);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Airport getAirport(@PathVariable Integer id) {
        return airportService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirportById(@PathVariable Integer id) {
        airportService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Airport updateAirport(@RequestBody @Valid AirportDto airportDto,
                                 @PathVariable Integer id) {
        var airport = airportMapper.toAirport(airportDto);
        return airportService.updateById(id, airport);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Airport updateOrCreateAirport(@RequestBody @Valid AirportDto airportDto,
                                         @PathVariable Integer id) {
        var airport = airportMapper.toAirport(airportDto);
        return airportService.updateOrCreateById(id, airport);
    }

}
