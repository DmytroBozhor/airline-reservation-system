package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
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
        return flightDetailService.save(flightDetail);
    }

//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAirport(@RequestBody @Valid AirportDto airportDto) {
//        var airport = flightDetailMapper.toAirport(airportDto);
//        flightDetailService.delete(airport);
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Airport getAirport(@PathVariable Integer id) {
//        return flightDetailService.findById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAirportById(@PathVariable Integer id) {
//        flightDetailService.deleteById(id);
//    }
//
//    @PatchMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Airport updateAirport(@RequestBody @Valid AirportDto airportDto,
//                                 @PathVariable Integer id) {
//        var airport = flightDetailMapper.toAirport(airportDto);
//        return flightDetailService.updateById(id, airport);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Airport updateOrCreateAirport(@RequestBody @Valid AirportDto airportDto,
//                                         @PathVariable Integer id) {
//        var airport = flightDetailMapper.toAirport(airportDto);
//        return flightDetailService.updateOrCreateById(id, airport);
//    }

}
