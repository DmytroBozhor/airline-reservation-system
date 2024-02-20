package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractTravelClassService;
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
    public TravelClassDto getTravelClassById(@PathVariable Long id) {
        return travelClassMapper.toTravelClassDto(travelClassService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto deleteTravelClassById(@PathVariable Long id) {
        return travelClassMapper.toTravelClassDto(travelClassService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto updateTravelClass(
            @RequestBody @Valid TravelClassPartialUpdateDto travelClassDto, @PathVariable Long id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateById(id, travelClass));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto updateOrCreateTravelClass(
            @RequestBody @Valid TravelClassSaveDto travelClassDto, @PathVariable Long id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateOrCreateById(id, travelClass));
    }

}
