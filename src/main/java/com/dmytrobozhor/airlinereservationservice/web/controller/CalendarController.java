package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.CalendarDto;
import com.dmytrobozhor.airlinereservationservice.dto.CalendarPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractCalendarService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.CalendarMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final AbstractCalendarService calendarService;

    private final CalendarMapper calendarMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CalendarDto> getAllCalendars() {
        return calendarMapper.toCalendarDto(calendarService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalendarDto saveCalendar(@RequestBody @Valid CalendarDto calendarDto) {
        var calendar = calendarMapper.toCalendar(calendarDto);
        return calendarMapper.toCalendarDto(calendarService.save(calendar));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarDto getCalendarById(@PathVariable Date id) {
        return calendarMapper.toCalendarDto(calendarService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCalendarById(@PathVariable Date id) {
        calendarService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarDto updateCalendar(
            @RequestBody @Valid CalendarPartialUpdateDto calendarDto, @PathVariable Date id) {
        var calendar = calendarMapper.toCalendar(calendarDto);
        return calendarMapper.toCalendarDto(calendarService.updateById(id, calendar));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarDto updateOrCreateCalendar(
            @RequestBody @Valid CalendarDto calendarDto, @PathVariable Date id) {
        var calendar = calendarMapper.toCalendar(calendarDto);
        return calendarMapper.toCalendarDto(calendarService.updateOrCreateById(id, calendar));
    }

}
