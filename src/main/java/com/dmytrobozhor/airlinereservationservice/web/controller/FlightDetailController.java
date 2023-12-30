package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fright-details")
@RequiredArgsConstructor
@Slf4j
public class FlightDetailController {

    private final AbstractFlightDetailService flightDetailService;

    private final AbstractAirportService airportService;

    private final FlightDetailMapper flightDetailMapper;

//    TODO: return dto instead of actual entities in all controllers
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDetail> getAllFlightDetails() {
        return flightDetailService.findAll();
    }


    //    TODO: implement annotation @CheckIfPersisted to check
    //     if the nested objects are already stored in the database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDetail saveFlightDetail(@RequestBody @Valid FlightDetailDto flightDetailDto) {
        var flightDetail = flightDetailMapper.toFlightDetail(flightDetailDto);
        return flightDetailService.save(flightDetail);
    }

    //    TODO: refactor code so we dont need to query the database
    //     to check if airports exist (use annotation as above or use entity manager maybe)
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

//    TODO: return something other than void in all controllers
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
