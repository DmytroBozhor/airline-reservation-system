package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import com.dmytrobozhor.airlinereservationservice.repository.CalendarRepository;
import com.dmytrobozhor.airlinereservationservice.service.service.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.CalendarMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: logging all over the app
@Service
@Transactional
@Slf4j
public class CalendarService extends ServiceBase<Calendar, Long> {

    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository, CalendarMapper calendarMapper) {
        super(calendarRepository, calendarMapper);
        this.calendarRepository = calendarRepository;
        this.calendarMapper = calendarMapper;
    }
}
