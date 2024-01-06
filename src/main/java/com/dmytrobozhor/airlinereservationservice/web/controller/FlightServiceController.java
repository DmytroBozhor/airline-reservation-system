package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightServiceService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightServiceMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.TravelClassMapper;
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

    private final TravelClassMapper travelClassMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightServiceDto> getAllFlightServices() {
        return flightServiceMapper.toFlightServiceDto(flightServiceService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightServiceDto saveFlightService(
            @RequestBody @Valid FlightServiceSaveDto flightServiceDto) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(flightServiceService.save(flightService));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceDto getFlightServiceById(@PathVariable Integer id) {
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
            @RequestBody @Valid FlightServiceSaveDto flightServiceDto, @PathVariable Integer id) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(flightServiceService.updateById(id, flightService));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceDto updateOrCreateFlightService(
            @RequestBody @Valid FlightServiceSaveDto flightServiceDto, @PathVariable Integer id) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(
                flightServiceService.updateOrCreateById(id, flightService));
    }

    @GetMapping("/{id}/get-travel-classes")
    @ResponseStatus(HttpStatus.OK)
    public List<TravelClassDto> getTravelClassesByFlightServiceId(@PathVariable Integer id) {
        var flightService = flightServiceService.findById(id);
        return travelClassMapper.toTravelClassDto(flightService.getTravelClasses());
    }

}
