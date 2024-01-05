package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.PassengerDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractPassengerService;
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
    public List<PassengerDto> getAllPassengers() {
        return passengerMapper.toPassengerDto(passengerService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerDto savePassenger(@RequestBody @Valid PassengerCreateDto passengerDto) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerMapper.toPassengerDto(passengerService.save(passenger));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassenger(@RequestBody @Valid PassengerDto passengerDto) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        passengerService.delete(passenger);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDto getPassenger(@PathVariable Integer id) {
        return passengerMapper.toPassengerDto(passengerService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassengerById(@PathVariable Integer id) {
        passengerService.deleteById(id);
    }

    //   TODO: make it not throw exception if the phone number did not change
    //   TODO: and it also does not throw EntityNotFoundException because @Unique validating annotation does it first
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDto updatePassenger(
            @RequestBody @Valid PassengerUpdateDto passengerDto, @PathVariable Integer id) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerMapper.toPassengerDto(passengerService.updateById(id, passenger));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDto updateOrCreatePassenger(
            @RequestBody @Valid PassengerCreateDto passengerDto, @PathVariable Integer id) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerMapper.toPassengerDto(passengerService.updateOrCreateById(id, passenger));
    }

}
