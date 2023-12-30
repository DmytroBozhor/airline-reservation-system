package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractPassengerService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PassengerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final AbstractPassengerService passengerService;

    private final PassengerMapper passengerMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Passenger> getAllPassengers() {
        return passengerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Passenger savePassenger(@RequestBody @Valid PassengerDto passengerDto) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerService.save(passenger);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassenger(@RequestBody @Valid PassengerDto passengerDto) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        passengerService.delete(passenger);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Passenger getPassenger(@PathVariable Integer id) {
        return passengerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassengerById(@PathVariable Integer id) {
        passengerService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Passenger updatePassenger(@RequestBody @Valid PassengerDto passengerDto,
                                     @PathVariable Integer id) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerService.updateById(id, passenger);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Passenger updateOrCreatePassenger(@RequestBody @Valid PassengerDto passengerDto,
                                             @PathVariable Integer id) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerService.updateOrCreateById(id, passenger);
    }

}
