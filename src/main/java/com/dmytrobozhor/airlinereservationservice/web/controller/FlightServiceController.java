package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightServiceService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightServiceMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-services")
@RequiredArgsConstructor
public class FlightServiceController {

    private final AbstractFlightServiceService flightServiceService;

    private final FlightServiceMapper flightServiceMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightServiceDto> getAllFlightServices() {
        return flightServiceMapper.toFlightServiceDto(flightServiceService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightServiceDto saveFlightService(@RequestBody @Valid FlightServiceDto flightServiceDto) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(flightServiceService.save(flightService));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightService(@RequestBody @Valid FlightServiceDto flightServiceDto) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        flightServiceService.delete(flightService);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceDto getFlightService(@PathVariable Integer id) {
        return flightServiceMapper.toFlightServiceDto(flightServiceService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightServiceById(@PathVariable Integer id) {
        flightServiceService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceDto updateFlightService(
            @RequestBody @Valid FlightServiceUpdateDto flightServiceDto, @PathVariable Integer id) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(
                flightServiceService.updateById(id, flightService));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceDto updateOrCreateFlightService(
            @RequestBody @Valid FlightServiceDto flightServiceDto, @PathVariable Integer id) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(
                flightServiceService.updateOrCreateById(id, flightService));
    }

}
