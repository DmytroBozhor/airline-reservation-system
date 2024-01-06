package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.ReservationDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractReservationService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final AbstractReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDto> getAllReservations() {
        return reservationMapper.toReservationDto(reservationService.findAll());
    }

//    TODO: make timestamp field not required (remove @NotNUll in Dto).
//     I do not fix it now because it throws exception for some reason and I have no time
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto saveReservation(@RequestBody @Valid ReservationSaveDto reservationDto) {
        var reservation = reservationMapper.toReservation(reservationDto);
        return reservationMapper.toReservationDto(reservationService.save(reservation));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDto getReservationById(@PathVariable Integer id) {
        return reservationMapper.toReservationDto(reservationService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservationById(@PathVariable Integer id) {
        reservationService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDto updateReservation(
            @RequestBody @Valid ReservationPartialUpdateDto reservationDto, @PathVariable Integer id) {
        var reservation = reservationMapper.toReservation(reservationDto);
        return reservationMapper.toReservationDto(reservationService.updateById(id, reservation));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDto updateOrCreateReservation(
            @RequestBody @Valid ReservationSaveDto reservationDto, @PathVariable Integer id) {
        var reservation = reservationMapper.toReservation(reservationDto);
        return reservationMapper.toReservationDto(reservationService.updateOrCreateById(id, reservation));
    }

}
