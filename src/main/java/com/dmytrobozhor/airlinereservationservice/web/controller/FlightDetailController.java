package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/fright-details")
@RequiredArgsConstructor
@Slf4j
public class FlightDetailController {

    private final AbstractFlightDetailService flightDetailService;

    private final AbstractAirportService airportService;

    private final FlightDetailMapper flightDetailMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDetail> getAllFlightDetails() {
        return flightDetailService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDetail saveFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        Airport sourceAirport = flightDetail.getSourceAirport();
        Airport destinationAirport = flightDetail.getDestinationAirport();
        log.debug("Checking if the airports already exist in the database.");
        airportService.findByAllFields(sourceAirport).ifPresent(flightDetail::setSourceAirport);
        airportService.findByAllFields(destinationAirport).ifPresent(flightDetail::setDestinationAirport);
        return flightDetailService.save(flightDetail);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        flightDetailService.delete(flightDetail);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetail getFlightDetail(@PathVariable Integer id) {
        return flightDetailService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightDetailById(@PathVariable Integer id) {
        flightDetailService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetail updateFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto,
                                           @PathVariable Integer id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailService.updateById(id, flightDetail);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetail updateOrCreateFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto,
                                                   @PathVariable Integer id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailService.updateOrCreateById(id, flightDetail);
    }

}
