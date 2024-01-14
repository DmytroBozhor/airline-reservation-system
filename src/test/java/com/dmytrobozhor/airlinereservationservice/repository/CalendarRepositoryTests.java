package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Flight Calendar Repository Tests")
@DataJpaTest
class CalendarRepositoryTests {

    @Autowired
    private CalendarRepository calendarRepository;

    private Calendar calendar;

    @BeforeEach
    void setUp() {

        calendar = Calendar
                .builder()
                .date(Date.valueOf(LocalDate.now()))
                .businessDay(true)
                .build();

    }

    @Test
    @DisplayName("save flight calendar")
    void whenSave_thenReturnSavedFlightCalendarWithId() {

        var savedFlightCalendar = calendarRepository.save(calendar);

        assertAll(
                () -> assertThat(savedFlightCalendar.getDate()).isNotNull(),
                () -> assertThat(savedFlightCalendar).isEqualTo(calendar)
        );

    }

    @Test
    @DisplayName("find flight calendar by existing id")
    void whenFindById_thenReturnFlightCalendar() {

        var savedFlightCalendar = calendarRepository.save(calendar);

        var flightCalendarOptional = calendarRepository.findById(savedFlightCalendar.getDate());

        assertAll(
                () -> assertThat(flightCalendarOptional).isPresent(),
                () -> assertThat(flightCalendarOptional.get()).isEqualTo(savedFlightCalendar)
        );

    }

    @Test
    @DisplayName("find flight calendar by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var flightCalendarOptional = calendarRepository.findById(Date.valueOf(LocalDate.now().plusDays(1)));

        assertThat(flightCalendarOptional).isEmpty();

    }

    @Test
    @DisplayName("find all flight calendars")
    void whenFindAll_thenReturnAllFlightCalendars() {

        var flightCalendars = Collections.singletonList(calendar);

        calendarRepository.saveAll(flightCalendars);

        var savedFlightCalendars = calendarRepository.findAll();

        assertAll(
                () -> assertThat(savedFlightCalendars).isNotEmpty(),
                () -> assertThat(savedFlightCalendars).hasSameSizeAs(flightCalendars),
                () -> assertThat(savedFlightCalendars).isEqualTo(flightCalendars)
        );

    }

    @Test
    @DisplayName("save all flight calendars")
    void whenSaveAll_thenReturnSavedFlightCalendars() {

        var flightCalendars = Collections.singletonList(calendar);

        var savedFlightCalendars = calendarRepository.saveAll(flightCalendars);

        assertAll(
                () -> assertThat(savedFlightCalendars).isNotEmpty(),
                () -> assertThat(savedFlightCalendars).hasSameSizeAs(flightCalendars),
                () -> assertThat(savedFlightCalendars).isEqualTo(flightCalendars)
        );

    }

    @Test
    @DisplayName("delete flight calendar by id")
    void whenDeleteById_thenReturnNothing() {

        var savedFlightCalendar = calendarRepository.save(calendar);

        calendarRepository.deleteById(savedFlightCalendar.getDate());

        var flightCalendarOptional = calendarRepository.findById(savedFlightCalendar.getDate());

        assertThat(flightCalendarOptional).isEmpty();

    }

    @Test
    @DisplayName("delete flight calendar")
    void whenDelete_thenReturnNothing() {

        var savedFlightCalendar = calendarRepository.save(calendar);

        calendarRepository.delete(savedFlightCalendar);

        var flightCalendarOptional = calendarRepository.findById(savedFlightCalendar.getDate());

        assertThat(flightCalendarOptional).isEmpty();

    }
}
