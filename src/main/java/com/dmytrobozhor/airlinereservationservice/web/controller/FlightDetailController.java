package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.FlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail.FlightDetailMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-details")
@RequiredArgsConstructor
@Slf4j
public class FlightDetailController {

    private final FlightDetailService flightDetailService;

    private final FlightDetailMapper flightDetailMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDetailReadDto> getAllFlightDetails() {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDetailReadDto saveFlightDetail(@RequestBody @Valid FlightDetailCreateDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.save(flightDetail));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailReadDto getFlightDetailById(@PathVariable Long id) {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailReadDto deleteFlightDetailById(@PathVariable Long id) {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailReadDto updateFlightDetail(
            @RequestBody @Valid FlightDetailPartialUpdateDto flightDetailDto, @PathVariable Long id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.updateById(id, flightDetail));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailReadDto updateOrCreateFlightDetail(
            @RequestBody @Valid FlightDetailCreateDto flightDetailDto, @PathVariable Long id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.updateOrCreateById(id, flightDetail));
    }

}
