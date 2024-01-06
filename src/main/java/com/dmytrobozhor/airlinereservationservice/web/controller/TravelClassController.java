package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractTravelClassService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightServiceMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.TravelClassMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-classes")
@RequiredArgsConstructor
public class TravelClassController {

    private final AbstractTravelClassService travelClassService;

    private final TravelClassMapper travelClassMapper;

    private final FlightServiceMapper flightServiceMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TravelClassDto> getAllTravelClasses() {
        return travelClassMapper.toTravelClassDto(travelClassService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelClassDto saveTravelClass(@RequestBody @Valid TravelClassSaveDto travelClassDto) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.save(travelClass));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto getTravelClassById(@PathVariable Integer id) {
        return travelClassMapper.toTravelClassDto(travelClassService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTravelClassById(@PathVariable Integer id) {
        travelClassService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto updateTravelClass(
            @RequestBody @Valid TravelClassPartialUpdateDto travelClassDto, @PathVariable Integer id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateById(id, travelClass));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto updateOrCreateTravelClass(
            @RequestBody @Valid TravelClassSaveDto travelClassDto, @PathVariable Integer id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateOrCreateById(id, travelClass));
    }

    @GetMapping("/{id}/get-flight-services")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightServiceDto> getFlightServicesByTravelClasId(@PathVariable Integer id) {
        var travelClass = travelClassService.findById(id);
        return flightServiceMapper.toFlightServiceDto(travelClass.getFlightServices());
    }

}
