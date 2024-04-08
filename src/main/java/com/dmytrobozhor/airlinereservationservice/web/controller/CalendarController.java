package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.CalendarCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.CalendarPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.CalendarReadDto;
import com.dmytrobozhor.airlinereservationservice.service.CalendarService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.calendar.CalendarMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    private final CalendarMapper calendarMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CalendarReadDto> getAllCalendars() {
        return calendarMapper.toCalendarDto(calendarService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalendarReadDto saveCalendar(@RequestBody @Valid CalendarCreateDto calendarDto) {
        var calendar = calendarMapper.toCalendar(calendarDto);
        return calendarMapper.toCalendarDto(calendarService.save(calendar));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarReadDto getCalendarById(@PathVariable Long id) {
        return calendarMapper.toCalendarDto(calendarService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarReadDto deleteCalendarById(@PathVariable Long id) {
        return calendarMapper.toCalendarDto(calendarService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarReadDto updateCalendar(
            @RequestBody @Valid CalendarPartialUpdateDto calendarDto, @PathVariable Long id) {
        var calendar = calendarMapper.toCalendar(calendarDto);
        return calendarMapper.toCalendarDto(calendarService.updateById(id, calendar));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarReadDto updateOrCreateCalendar(
            @RequestBody @Valid CalendarCreateDto calendarDto, @PathVariable Long id) {
        var calendar = calendarMapper.toCalendar(calendarDto);
        return calendarMapper.toCalendarDto(calendarService.updateOrCreateById(id, calendar));
    }

}
