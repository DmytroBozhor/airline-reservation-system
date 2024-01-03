package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightCostService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightCostMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-costs")
@RequiredArgsConstructor
public class FlightCostController {

    private final AbstractFlightCostService flightCostService;

    private final FlightCostMapper flightCostMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightCostDto> getAllFlightCosts() {
        return flightCostMapper.toFlightCostDto(flightCostService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightCostDto saveFlightCost(@RequestBody @Valid FlightCostDto flightCostDto) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.save(flightCost));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightCost(@RequestBody @Valid FlightCostDto flightCostDto) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        flightCostService.delete(flightCost);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostDto getFlightCost(@PathVariable Integer id) {
        return flightCostMapper.toFlightCostDto(flightCostService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightCostById(@PathVariable Integer id) {
        flightCostService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostDto updateFlightCost(
            @RequestBody @Valid FlightCostUpdateDto flightCostDto, @PathVariable Integer id) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.updateById(id, flightCost));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostDto updateOrCreateFlightCost(
            @RequestBody @Valid FlightCostDto flightCostDto, @PathVariable Integer id) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.updateOrCreateById(id, flightCost));
    }

}
