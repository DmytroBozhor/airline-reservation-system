package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassUpdateDto;
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

    //    TODO: saves the nested entities even if they exist
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatDetailDto saveSeatDetail(@RequestBody @Valid SeatDetailDto seatDetailDto) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        seatDetailService.fetchDataIfExist(seatDetail);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.save(seatDetail));
    }

    //    TODO: findByAllFields expected to return on entity but actually may return several
    //     so either fix it or delete this endpoint in all controllers for good
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeatDetail(@RequestBody @Valid SeatDetailDto seatDetailDto) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        seatDetailService.fetchDataIfExist(seatDetail);
        seatDetailService.delete(seatDetail);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailDto getSeatDetail(@PathVariable Integer id) {
        return seatDetailMapper.toSeatDetailDto(seatDetailService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeatDetailById(@PathVariable Integer id) {
        seatDetailService.deleteById(id);
    }

    //    TODO: does not actually update the flight detail and travel class
    //     but only creates new ones and assign them to the seat detail
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailDto updateSeatDetail(@RequestBody @Valid SeatDetailUpdateDto seatDetailDto,
                                          @PathVariable Integer id) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        seatDetailService.fetchDataIfExist(seatDetail);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.updateById(id, seatDetail));
    }

    //    TODO: does not actually update the flight detail and travel class
    //     but only creates new ones and assign them to the seat detail
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatDetailDto updateOrCreateSeatDetail(@RequestBody @Valid SeatDetailDto seatDetailDto,
                                                  @PathVariable Integer id) {
        var seatDetail = seatDetailMapper.toSeatDetail(seatDetailDto);
        seatDetailService.fetchDataIfExist(seatDetail);
        return seatDetailMapper.toSeatDetailDto(seatDetailService.updateOrCreateById(id, seatDetail));
    }

}
