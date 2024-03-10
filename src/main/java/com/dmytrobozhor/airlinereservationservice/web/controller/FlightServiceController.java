package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServicePartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceReadDto;
import com.dmytrobozhor.airlinereservationservice.service.FlightServiceService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.flightservice.FlightServiceMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.travelclass.TravelClassMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-services")
@RequiredArgsConstructor
public class FlightServiceController {

    private final FlightServiceService flightServiceService;

    private final FlightServiceMapper flightServiceMapper;

//    private final TravelClassMapper travelClassMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightServiceReadDto> getAllFlightServices() {
        return flightServiceMapper.toFlightServiceDto(flightServiceService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightServiceReadDto saveFlightService(
            @RequestBody @Valid FlightServiceCreateDto flightServiceDto) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(flightServiceService.save(flightService));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceReadDto getFlightServiceById(@PathVariable Long id) {
        return flightServiceMapper.toFlightServiceDto(flightServiceService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceReadDto deleteFlightServiceById(@PathVariable Long id) {
        return flightServiceMapper.toFlightServiceDto(flightServiceService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceReadDto updateFlightService(
            @RequestBody @Valid FlightServicePartialUpdateDto flightServiceDto, @PathVariable Long id) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(flightServiceService.updateById(id, flightService));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightServiceReadDto updateOrCreateFlightService(
            @RequestBody @Valid FlightServiceCreateDto flightServiceDto, @PathVariable Long id) {
        var flightService = flightServiceMapper.toFlightService(flightServiceDto);
        return flightServiceMapper.toFlightServiceDto(
                flightServiceService.updateOrCreateById(id, flightService));
    }

    /*@GetMapping("/{id}/get-travel-classes")
    @ResponseStatus(HttpStatus.OK)
    public List<TravelClassDto> getTravelClassesByFlightServiceId(@PathVariable Long id) {
        var flightService = flightServiceService.findById(id);
        return travelClassMapper.toTravelClassDto(flightService.getTravelClasses());
    }*/

}
