package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.FlightCostDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightCostService;
import com.dmytrobozhor.airlinereservationservice.domain.compositeid.FlightCostId;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightCostMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
    public FlightCostDto saveFlightCost(@RequestBody @Valid FlightCostSaveDto flightCostDto) {
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        var flightCostId = FlightCostId.builder()
                .validFromDateId(flightCost.getValidFromDate().getDate())
                .seatDetailId(flightCost.getSeatDetail().getId()).build();
        flightCost.setId(flightCostId);
        return flightCostMapper.toFlightCostDto(flightCostService.save(flightCost));
    }

//  TODO: make and use constants for endpoints replacing this long string
    @GetMapping("/seat-detail/{seatDetailId}/date/{validFromDateId}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostDto getFlightCost(@PathVariable Integer seatDetailId, @PathVariable Date validFromDateId) {
        var flightCostId = FlightCostId.builder().seatDetailId(seatDetailId).validFromDateId(validFromDateId).build();
        return flightCostMapper.toFlightCostDto(flightCostService.findById(flightCostId));
    }

    @DeleteMapping("/seat-detail/{seatDetailId}/date/{validFromDateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightCostById(@PathVariable Integer seatDetailId, @PathVariable Date validFromDateId) {
        var flightCostId = FlightCostId.builder().seatDetailId(seatDetailId).validFromDateId(validFromDateId).build();
        flightCostService.deleteById(flightCostId);
    }

    @PatchMapping("/seat-detail/{seatDetailId}/date/{validFromDateId}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostDto updateFlightCost(
            @RequestBody @Valid FlightCostPartialUpdateDto flightCostDto,
            @PathVariable Integer seatDetailId, @PathVariable Date validFromDateId) {
        var flightCostId = FlightCostId.builder().seatDetailId(seatDetailId).validFromDateId(validFromDateId).build();
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.updateById(flightCostId, flightCost));
    }

//    TODO: decide whether I need this endpoint or not. It is a little confusing
//     since the seatDetail and validFromDate are not insertable or updatable.
    /*@PutMapping("/seat-detail/{seatDetailId}/date/{validFromDateId}")
    @ResponseStatus(HttpStatus.OK)
    public FlightCostDto updateOrCreateFlightCost(
            @RequestBody @Valid FlightCostSaveDto flightCostDto,
            @PathVariable Integer seatDetailId, @PathVariable Date validFromDateId) {
        var flightCostId = FlightCostId.builder().seatDetailId(seatDetailId).validFromDateId(validFromDateId).build();
        var flightCost = flightCostMapper.toFlightCost(flightCostDto);
        return flightCostMapper.toFlightCostDto(flightCostService.updateOrCreateById(flightCostId, flightCost));
    }*/

}
