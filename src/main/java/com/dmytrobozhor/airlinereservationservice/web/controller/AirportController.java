package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.AirportReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AirportService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.airport.AirportMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO: implement PL/SQL triggers for database
@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    private final AirportMapper airportMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AirportReadDto> getAllAirports() {
        return airportMapper.toAirportDto(airportService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirportReadDto saveAirport(@RequestBody @Valid AirportCreateDto airportDto) {
        var airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDto(airportService.save(airport));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportReadDto getAirportById(@PathVariable Long id) {
        return airportMapper.toAirportDto(airportService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportReadDto deleteAirportById(@PathVariable Long id) {
        return airportMapper.toAirportDto(airportService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportReadDto updateAirport(
            @RequestBody @Valid AirportPartialUpdateDto airportDto, @PathVariable Long id) {
        var airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDto(airportService.updateById(id, airport));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportReadDto updateOrCreateAirport(
            @RequestBody @Valid AirportCreateDto airportDto, @PathVariable Long id) {
        var airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDto(airportService.updateOrCreateById(id, airport));
    }

}
