package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.TravelClassCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.TravelClassService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.travelclass.TravelClassMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-classes")
@RequiredArgsConstructor
public class TravelClassController {

    private final TravelClassService travelClassService;

    private final TravelClassMapper travelClassMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TravelClassReadDto> getAllTravelClasses() {
        return travelClassMapper.toTravelClassDto(travelClassService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelClassReadDto saveTravelClass(@RequestBody @Valid TravelClassCreateDto travelClassDto) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.save(travelClass));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassReadDto getTravelClassById(@PathVariable Long id) {
        return travelClassMapper.toTravelClassDto(travelClassService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassReadDto deleteTravelClassById(@PathVariable Long id) {
        return travelClassMapper.toTravelClassDto(travelClassService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassReadDto updateTravelClass(
            @RequestBody @Valid TravelClassPartialUpdateDto travelClassDto, @PathVariable Long id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateById(id, travelClass));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassReadDto updateOrCreateTravelClass(
            @RequestBody @Valid TravelClassCreateDto travelClassDto, @PathVariable Long id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateOrCreateById(id, travelClass));
    }

}
