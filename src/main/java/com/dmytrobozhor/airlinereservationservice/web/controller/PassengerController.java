package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.PassengerCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.PassengerService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.passenger.PassengerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    private final PassengerMapper passengerMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PassengerReadDto> getAllPassengers() {
        return passengerMapper.toPassengerDto(passengerService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerReadDto savePassenger(@RequestBody @Valid PassengerCreateDto passengerDto) {
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerMapper.toPassengerDto(passengerService.save(passenger));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerReadDto getPassengerById(@PathVariable Long id) {
        return passengerMapper.toPassengerDto(passengerService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerReadDto deletePassengerById(@PathVariable Long id) {
        return passengerMapper.toPassengerDto(passengerService.deleteById(id));
    }

    //   TODO: make hibernate validation for phone number field instead of jpa validation
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerReadDto updatePassenger(
            @RequestBody @Valid PassengerPartialUpdateDto passengerDto, @PathVariable Long id) {
//        var passenger = passengerMapper.toPassenger(
//                passengerMapper.toPersonalInfo(passengerDto),
//                passengerMapper.toAdditionalInfo(passengerDto));
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerMapper.toPassengerDto(passengerService.updateById(id, passenger));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerReadDto updateOrCreatePassenger(
            @RequestBody @Valid PassengerCreateDto passengerDto, @PathVariable Long id) {
//        var passenger = passengerMapper.toPassenger(
//                passengerMapper.toPersonalInfo(passengerDto),
//                passengerMapper.toAdditionalInfo(passengerDto));
        var passenger = passengerMapper.toPassenger(passengerDto);
        return passengerMapper.toPassengerDto(passengerService.updateOrCreateById(id, passenger));
    }

}
