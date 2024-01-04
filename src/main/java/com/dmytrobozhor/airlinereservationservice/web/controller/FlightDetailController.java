package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public List<FlightDetailDto> getAllFlightDetails() {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.findAll());
    }

    //    TODO: implement annotation @CheckIfPersisted to check
    //     if the nested objects are already stored in the database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDetailDto saveFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        fetchAirportsIfExist(flightDetail);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.save(flightDetail));
    }

    //    TODO: refactor code so we dont need to query the database
    //     to check if airports exist (use annotation as above or use entity manager maybe)
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        fetchAirportsIfExist(flightDetail);
        flightDetailService.delete(flightDetail);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto getFlightDetail(@PathVariable Integer id) {
        return flightDetailMapper.toFlightDetailDto(flightDetailService.findById(id));
    }

    //    TODO: return something other than void in all controllers
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightDetailById(@PathVariable Integer id) {
        flightDetailService.deleteById(id);
    }

    //    TODO: update does not work because airports can be null -> we get exception
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto updateFlightDetail(
            @RequestBody @Valid FlightDetailUpdateDto flightDetailDto, @PathVariable Integer id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        fetchAirportsIfExist(flightDetail);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.updateById(id, flightDetail));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDetailDto updateOrCreateFlightDetail(
            @RequestBody @Valid FlightDetailDto flightDetailDto, @PathVariable Integer id) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        fetchAirportsIfExist(flightDetail);
        return flightDetailMapper.toFlightDetailDto(flightDetailService.updateOrCreateById(id, flightDetail));
    }

    private void fetchAirportsIfExist(FlightDetail flightDetail) {
        Airport sourceAirport = flightDetail.getSourceAirport();
        Airport destinationAirport = flightDetail.getDestinationAirport();
        log.debug("Checking if airports exist");
        Optional<Airport> sourceAirportOptional = airportService.findByAllFields(sourceAirport);
        Optional<Airport> destinationAirportOptional = airportService.findByAllFields(destinationAirport);
        sourceAirportOptional.ifPresent(airport -> {
            log.debug("The source airport already exists. Fetching...");
            flightDetail.setSourceAirport(airport);
        });
        destinationAirportOptional.ifPresent(airport -> {
            log.debug("The destination airport already exists. Fetching...");
            flightDetail.setDestinationAirport(airport);
        });
    }

}
