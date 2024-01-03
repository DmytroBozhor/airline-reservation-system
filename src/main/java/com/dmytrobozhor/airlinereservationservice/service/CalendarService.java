package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import com.dmytrobozhor.airlinereservationservice.repository.CalendarRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.CalendarMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService implements AbstractCalendarService {

    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

    @Override
    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    @Override
    public Calendar save(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @Override
    public void deleteById(Integer id) {
        Calendar calendar = calendarRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        calendarRepository.delete(calendar);
    }

    @Override
    public void delete(Calendar calendar) {
        Calendar persistedCalendar = calendarRepository
                .findByAllFields(calendar).orElseThrow(EntityNotFoundException::new);
        calendarRepository.delete(persistedCalendar);
    }

    @Override
    public Calendar findById(Integer id) {
        return calendarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Calendar updateById(Integer id, Calendar calendar) {
        return calendarRepository.findById(id).map(persistedCalendar -> {
            calendarMapper.updateCalendarPartial(persistedCalendar, calendar);
            return calendarRepository.save(persistedCalendar);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Calendar updateOrCreateById(Integer id, Calendar calendar) {
        return calendarRepository.findById(id).map(persistedCalendar -> {
            calendarMapper.updateCalendarPartial(persistedCalendar, calendar);
            return calendarRepository.save(persistedCalendar);
        }).orElse(calendarRepository.save(calendar));
    }
}
