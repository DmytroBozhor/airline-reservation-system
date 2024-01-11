package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("Airport Service Tests")
//@DataJpaTest
@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    //    @Autowired
    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private AirportMapper airportMapper;

    private Airport airport;

    @BeforeEach
    void setUp() {
        airport = Airport
                .builder()
                .name("dummy")
                .city("dummy")
                .country("dummy")
                .build();
    }

    @Test
    void findAll() {

        List<Airport> airportsForSave = Collections.singletonList(airport);
        airportService.saveAll(airportsForSave);

        doReturn(airportsForSave).when(airportRepository).findAll();

        var airports = airportService.findAll();

        assertAll(
                () -> assertThat(airports).isNotEmpty(),
                () -> assertThat(airports).hasSameSizeAs(airportsForSave),
                () -> assertThat(airports).isEqualTo(airportsForSave)
        );

        verify(airportRepository, atLeastOnce()).findAll();
    }

    @Test
    void save() {

        doReturn(airport).when(airportRepository).save(any(Airport.class));

        var savedAirport = airportService.save(airport);

        assertThat(savedAirport).isEqualTo(airport);

        verify(airportRepository, atLeastOnce()).save(any(Airport.class));
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void updateOrCreateById() {
    }

    @Test
    void findByAllFields() {
    }
}