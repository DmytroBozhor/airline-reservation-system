package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.SeatDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.seatdetail.SeatDetailMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat-details")
@RequiredArgsConstructor
public class SeatDetailController {

    private final SeatDetailService seatDetailService;

    private final SeatDetailMapper seatDetailMapper;

    //    TODO: makes a lot of queries -> fix
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeatDetailReadDto> getAllSeatDetails() {
        return seatDetailMapper.toSeatDetailDto(seatDetailService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatDetailReadDto saveSeatDetail(@RequestBody @Valid SeatDetailCreateDto seatDetailDto) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.save(seatDetail));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailReadDto getSeatDetailById(@PathVariable Long id) {
        return seatDetailMapper.toSeatDetailDto(seatDetailService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailReadDto deleteSeatDetailById(@PathVariable Long id) {
        return seatDetailMapper.toSeatDetailDto(seatDetailService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailReadDto updateSeatDetail(
            @RequestBody @Valid SeatDetailPartialUpdateDto seatDetailDto, @PathVariable Long id) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.updateById(id, seatDetail));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailReadDto updateOrCreateSeatDetail(
            @RequestBody @Valid SeatDetailCreateDto seatDetailDto, @PathVariable Long id) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.updateOrCreateById(id, seatDetail));
    }

}
