package com.dmytrobozhor.airlinereservationservice.util.mappers.calendar;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import com.dmytrobozhor.airlinereservationservice.dto.CalendarCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.CalendarReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.CalendarPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMappingConfig.class)
public interface CalendarMapper extends UpdatePartiallyMapper<Calendar, Long> {

    Calendar toCalendar(CalendarReadDto calendarDto);

    Calendar toCalendar(CalendarCreateDto calendarDto);

    Calendar toCalendar(CalendarPartialUpdateDto calendarDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "flightCostsFromDate", ignore = true)
    @Mapping(target = "flightCostsToDate", ignore = true)
    Calendar updatePartially(@MappingTarget Calendar persistedCalendar, Calendar calendar);

    List<CalendarReadDto> toCalendarDto(List<Calendar> calendars);

    CalendarReadDto toCalendarDto(Calendar calendar);

}
