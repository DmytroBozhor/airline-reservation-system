package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractSeatDetailService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.SeatDetailMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat-details")
@RequiredArgsConstructor
public class SeatDetailController {

    private final AbstractSeatDetailService seatDetailService;

    private final SeatDetailMapper seatDetailMapper;

    //    TODO: makes a lot of queries -> fix
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeatDetailDto> getAllSeatDetails() {
        return seatDetailMapper.toSeatDetailDto(seatDetailService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatDetailDto saveSeatDetail(@RequestBody @Valid SeatDetailSaveDto seatDetailDto) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.save(seatDetail));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailDto getSeatDetailById(@PathVariable Integer id) {
        return seatDetailMapper.toSeatDetailDto(seatDetailService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeatDetailById(@PathVariable Integer id) {
        seatDetailService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailDto updateSeatDetail(@RequestBody @Valid SeatDetailPartialUpdateDto seatDetailDto,
                                          @PathVariable Integer id) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.updateById(id, seatDetail));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailDto updateOrCreateSeatDetail(@RequestBody @Valid SeatDetailSaveDto seatDetailDto,
                                                  @PathVariable Integer id) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.updateOrCreateById(id, seatDetail));
    }

}
