package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import com.dmytrobozhor.airlinereservationservice.repository.CalendarRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.CalendarMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("Calendar Service Tests")
@ExtendWith(MockitoExtension.class)
class CalendarServiceTests {

    @InjectMocks
    private CalendarService calendarService;

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private CalendarMapper calendarMapper;

    private Calendar calendar;

    @BeforeEach
    void setUp() {

        calendar = Calendar
                .builder()
                .date(Date.valueOf("2024-01-15"))
                .businessDay(false)
                .build();

    }

    @Test
    @DisplayName("save all calendars")
    void whenSaveAll_thenReturnSavedCalendars() {

        var calendarsForSave = Collections.singletonList(calendar);

        doReturn(calendarsForSave).when(calendarRepository).saveAll(any());

        var savedCalendars = calendarService.saveAll(calendarsForSave);

        assertAll(
                () -> assertThat(savedCalendars).isNotEmpty(),
                () -> assertThat(savedCalendars).hasSameSizeAs(calendarsForSave)
        );

        verify(calendarRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all calendars")
    void whenFindAll_thenReturnAllCalendars() {

        var calendars = Collections.singletonList(calendar);

        doReturn(calendars).when(calendarRepository).findAll();

        var savedCalendars = calendarService.findAll();

        assertAll(
                () -> assertThat(savedCalendars).isNotEmpty(),
                () -> assertThat(savedCalendars).hasSameSizeAs(calendars)
        );

        verify(calendarRepository).findAll();

    }

    @Test
    @DisplayName("save calendar")
    void whenSave_thenReturnSavedCalendar() {

        doReturn(calendar).when(calendarRepository).save(any());

        var savedCalendar = calendarService.save(calendar);

        assertAll(
                () -> assertThat(savedCalendar).isNotNull(),
                () -> assertThat(savedCalendar).isEqualTo(calendar)
        );

        verify(calendarRepository).save(any());

    }

    @Test
    @DisplayName("delete calendar by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(calendarRepository.findById(any(Date.class)))
                .thenReturn(Optional.of(calendar))
                .thenReturn(Optional.empty());

        doNothing().when(calendarRepository).delete(any());

        calendarService.deleteById(calendar.getDate());

        assertThrows(EntityNotFoundException.class, () -> calendarService.findById(calendar.getDate()));

        verify(calendarRepository, times(2)).findById(any(Date.class));
        verify(calendarRepository).delete(any());

    }

    @Test
    @DisplayName("delete calendar by not existing id")
    void whenDeleteById_thenThrowException() {

        when(calendarRepository.findById(any(Date.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> calendarService.deleteById(calendar.getDate()));

        verify(calendarRepository).findById(any(Date.class));
        verify(calendarRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find calendar by existing id")
    void whenFindById_thenReturnCalendar() {

        doReturn(Optional.of(calendar)).when(calendarRepository).findById(any(Date.class));

        var savedCalendar = calendarService.findById(calendar.getDate());

        assertThat(savedCalendar).isNotNull();

        verify(calendarRepository).findById(any(Date.class));

    }

    @Test
    @DisplayName("find calendar by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(calendarRepository).findById(any(Date.class));

        assertThrows(EntityNotFoundException.class, () -> calendarService.findById(calendar.getDate()));

        verify(calendarRepository).findById(any(Date.class));

    }

    @Test
    @DisplayName("update calendar by existing id")
    void whenUpdateById_thenReturnUpdatedCalendar() {

        var persistedCalendar = Calendar
                .builder()
                .date(Date.valueOf("2024-01-15"))
                .businessDay(false)
                .build();

        doReturn(Optional.of(persistedCalendar)).when(calendarRepository).findById(any(Date.class));

        persistedCalendar.setBusinessDay(calendar.getBusinessDay());

        doNothing().when(calendarMapper).updateCalendarPartial(any(), any());

        doReturn(persistedCalendar).when(calendarRepository).save(any());

        var updatedCalendar = calendarService.updateById(persistedCalendar.getDate(), calendar);

        assertAll(
                () -> assertThat(updatedCalendar.getBusinessDay()).isEqualTo(calendar.getBusinessDay())
        );

        verify(calendarRepository).findById(any(Date.class));
        verify(calendarMapper).updateCalendarPartial(any(), any());
        verify(calendarRepository).save(any());

    }

    @Test
    @DisplayName("update calendar by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(calendarRepository).findById(any(Date.class));

        assertThrows(EntityNotFoundException.class,
                () -> calendarService.updateById(Date.valueOf("2024-02-18"), calendar));

        verify(calendarRepository).findById(any(Date.class));

    }

    @Test
    @DisplayName("update or create calendar by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedCalendar() {

        var persistedCalendar = Calendar
                .builder()
                .date(Date.valueOf("2024-01-15"))
                .businessDay(false)
                .build();

        doReturn(Optional.of(persistedCalendar)).when(calendarRepository).findById(any(Date.class));

        persistedCalendar.setBusinessDay(calendar.getBusinessDay());

        doNothing().when(calendarMapper).updateCalendarPartial(any(), any());

        doReturn(persistedCalendar).when(calendarRepository).save(any());

        var updatedCalendar = calendarService.updateOrCreateById(persistedCalendar.getDate(), calendar);

        assertAll(
                () -> assertThat(updatedCalendar.getBusinessDay()).isEqualTo(calendar.getBusinessDay())
        );

        verify(calendarRepository).findById(any(Date.class));
        verify(calendarMapper).updateCalendarPartial(any(), any());
        verify(calendarRepository).save(any());

    }

    @Test
    @DisplayName("update or create calendar by not existing id")
    void wheUpdateOrCreateById_thenCreateNewCalendar() {

        doReturn(Optional.empty()).when(calendarRepository).findById(any(Date.class));

        doReturn(calendar).when(calendarRepository).save(any());

        var createdCalendar = calendarService.updateOrCreateById(Date.valueOf("2024-02-18"), calendar);

        assertAll(
                () -> assertThat(createdCalendar).isNotNull(),
                () -> assertThat(createdCalendar).isEqualTo(calendar)
        );

        verify(calendarRepository).findById(any(Date.class));
        verify(calendarRepository).save(any());

    }
}
