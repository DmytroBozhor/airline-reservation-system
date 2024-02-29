package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.ReservationReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationCreateDto;
import com.dmytrobozhor.airlinereservationservice.service.ReservationService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.reservation.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationReadDto> getAllReservations() {
        return reservationMapper.toReservationDto(reservationService.findAll());
    }

//    TODO: make timestamp field not required (remove @NotNUll in Dto).
//     I do not fix it now because it throws exception for some reason and I have no time
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationReadDto saveReservation(@RequestBody @Valid ReservationCreateDto ReservationReadDto) {
        var reservation = reservationMapper.toReservation(ReservationReadDto);
        return reservationMapper.toReservationDto(reservationService.save(reservation));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationReadDto getReservationById(@PathVariable Long id) {
        return reservationMapper.toReservationDto(reservationService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationReadDto deleteReservationById(@PathVariable Long id) {
        return reservationMapper.toReservationDto(reservationService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationReadDto updateReservation(
            @RequestBody @Valid ReservationPartialUpdateDto ReservationReadDto, @PathVariable Long id) {
        var reservation = reservationMapper.toReservation(ReservationReadDto);
        return reservationMapper.toReservationDto(reservationService.updateById(id, reservation));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationReadDto updateOrCreateReservation(
            @RequestBody @Valid ReservationCreateDto ReservationReadDto, @PathVariable Long id) {
        var reservation = reservationMapper.toReservation(ReservationReadDto);
        return reservationMapper.toReservationDto(reservationService.updateOrCreateById(id, reservation));
    }

}
