package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.FlightCostCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostReadDto;
import com.dmytrobozhor.airlinereservationservice.service.FlightCostService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.flightcostmapper.FlightCostMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-costs")
@RequiredArgsConstructor
public class FlightCostController {

    private final FlightCostService flightCostService;

    private final FlightCostMapper flightCostMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightCostReadDto> getAllFlightCosts() {
        return flightCostMapper.toFlightCostDto(flightCostService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightCostReadDto saveFlightCost(@RequestBody @Valid FlightCostCreateDto flightCostDto) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.save(flightCost));
    }

    //  TODO: make and use constants for endpoints
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostReadDto getFlightCost(@PathVariable Long id) {
        return flightCostMapper.toFlightCostDto(flightCostService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostReadDto deleteFlightCostById(@PathVariable Long id) {
        return flightCostMapper.toFlightCostDto(flightCostService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostReadDto updateFlightCost(
            @RequestBody @Valid FlightCostPartialUpdateDto flightCostDto, @PathVariable Long id) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.updateById(id, flightCost));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostReadDto updateOrCreateFlightCost(
            @RequestBody @Valid FlightCostCreateDto flightCostDto, @PathVariable Long id) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.updateOrCreateById(id, flightCost));
    }

}
