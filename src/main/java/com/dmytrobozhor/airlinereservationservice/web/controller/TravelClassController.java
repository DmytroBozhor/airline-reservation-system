package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassUpdateDto;
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
    public TravelClassDto saveTravelClass(@RequestBody @Valid TravelClassDto travelClassDto) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.save(travelClass));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTravelClass(@RequestBody @Valid TravelClassDto travelClassDto) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        travelClassService.delete(travelClass);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto getTravelClass(@PathVariable Integer id) {
        return travelClassMapper.toTravelClassDto(travelClassService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTravelClassById(@PathVariable Integer id) {
        travelClassService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto updateTravelClass(@RequestBody @Valid TravelClassUpdateDto travelClassDto,
                                            @PathVariable Integer id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateById(id, travelClass));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelClassDto updateOrCreateTravelClass(@RequestBody @Valid TravelClassDto travelClassDto,
                                                    @PathVariable Integer id) {
        var travelClass = travelClassMapper.toTravelClass(travelClassDto);
        return travelClassMapper.toTravelClassDto(travelClassService.updateOrCreateById(id, travelClass));
    }

}
