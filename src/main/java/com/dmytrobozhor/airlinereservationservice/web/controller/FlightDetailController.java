package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.other.FlightDetailMapper;
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

    private final AbstractFlightDetailService flightDetailService;

    private final FlightDetailMapper flightDetailMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDetailDto> getAllFlightDetails() {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDetailDto saveFlightDetail(@RequestBody @Valid FlightDetailSaveDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.save(flightDetail));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto getFlightDetailById(@PathVariable Long id) {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto deleteFlightDetailById(@PathVariable Long id) {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto updateFlightDetail(
            @RequestBody @Valid FlightDetailPartialUpdateDto flightDetailDto, @PathVariable Long id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.updateById(id, flightDetail));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto updateOrCreateFlightDetail(
            @RequestBody @Valid FlightDetailSaveDto flightDetailDto, @PathVariable Long id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.updateOrCreateById(id, flightDetail));
    }

}
