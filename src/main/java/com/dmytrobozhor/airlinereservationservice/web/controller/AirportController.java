package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AbstractAirportService airportService;

    private final AirportMapper airportMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AirportDto> getAllAirports() {
        return airportMapper.toAirportDto(airportService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirportDto saveAirport(@RequestBody @Valid AirportDto airportDto) {
        var airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDto(airportService.save(airport));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirport(@RequestBody @Valid AirportDto airportDto) {
        var airport = airportMapper.toAirport(airportDto);
        airportService.delete(airport);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportDto getAirport(@PathVariable Integer id) {
        return airportMapper.toAirportDto(airportService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirportById(@PathVariable Integer id) {
        airportService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportDto updateAirport(
            @RequestBody @Valid AirportUpdateDto airportDto, @PathVariable Integer id) {
        var airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDto(airportService.updateById(id, airport));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirportDto updateOrCreateAirport(
            @RequestBody @Valid AirportDto airportDto, @PathVariable Integer id) {
        var airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDto(airportService.updateOrCreateById(id, airport));
    }

}
