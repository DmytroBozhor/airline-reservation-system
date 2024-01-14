package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("Airport Service Tests")
@ExtendWith(MockitoExtension.class)
class AirportServiceTests {

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
                .id(1)
                .name("Wong")
                .city("Bang")
                .country("Norway")
                .build();

    }

    @Test
    @DisplayName("find all airports")
    void whenSaveAll_thenReturnSavedAirports() {

        var airportsForSave = Collections.singletonList(airport);

        doReturn(airportsForSave).when(airportRepository).saveAll(anyCollection());

        airportService.saveAll(airportsForSave);

        doReturn(airportsForSave).when(airportRepository).findAll();

        var savedAirports = airportService.findAll();

        assertAll(
                () -> assertThat(savedAirports).isNotEmpty(),
                () -> assertThat(savedAirports).hasSameSizeAs(airportsForSave),
                () -> assertThat(savedAirports).isEqualTo(airportsForSave)
        );

        verify(airportRepository).findAll();
        verify(airportRepository).saveAll(anyCollection());

    }

    @Test
    @DisplayName("save airport")
    void whenSave_thenReturnSavedAirport() {

        doReturn(airport).when(airportRepository).save(any(Airport.class));

        var savedAirport = airportService.save(airport);

        assertAll(
                () -> assertThat(savedAirport).isNotNull(),
                () -> assertThat(savedAirport).isEqualTo(airport)
        );

        verify(airportRepository).save(any(Airport.class));

    }

    @Test
    @DisplayName("delete airport by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(airportRepository.findById(anyInt()))
                .thenReturn(Optional.of(airport))
                .thenReturn(Optional.empty());

        doNothing().when(airportRepository).delete(any(Airport.class));

        airportService.deleteById(airport.getId());

        assertThrows(EntityNotFoundException.class, () -> airportService.findById(airport.getId()));

        verify(airportRepository, times(2)).findById(anyInt());
        verify(airportRepository).delete(any(Airport.class));

    }

    @Test
    @DisplayName("delete airport by not existing id")
    void whenDeleteById_thenThrowException() {

        when(airportRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> airportService.deleteById(airport.getId()));

        verify(airportRepository).findById(anyInt());
        verify(airportRepository, never()).delete(any(Airport.class));

    }

    @Test
    @DisplayName("find airport by existing id")
    void whenFindById_thenReturnAirport() {

        doReturn(Optional.of(airport)).when(airportRepository).findById(anyInt());

        var savedAirport = airportService.findById(airport.getId());

        assertThat(savedAirport).isNotNull();

        verify(airportRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find airport by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(airportRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> airportService.findById(airport.getId()));

        verify(airportRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update airport by existing id")
    void whenUpdateById_thenReturnUpdatedAirport() {

        var persistedAirport = Airport.builder()
                .id(2)
                .name("Guan")
                .city("Chingo")
                .country("China")
                .build();

        doReturn(Optional.of(persistedAirport)).when(airportRepository).findById(anyInt());

        persistedAirport.setName(airport.getName());
        persistedAirport.setCity(airport.getCity());
        persistedAirport.setCountry(airport.getCountry());

        doNothing().when(airportMapper).updateAirportPartial(any(Airport.class), any(Airport.class));

        doReturn(persistedAirport).when(airportRepository).save(any(Airport.class));

        var updatedAirport = airportService.updateById(persistedAirport.getId(), airport);

        assertAll(
                () -> assertThat(updatedAirport.getName()).isEqualTo(airport.getName()),
                () -> assertThat(updatedAirport.getCity()).isEqualTo(airport.getCity()),
                () -> assertThat(updatedAirport.getCountry()).isEqualTo(airport.getCountry())
        );

        verify(airportRepository).findById(anyInt());
        verify(airportMapper).updateAirportPartial(any(Airport.class), any(Airport.class));
        verify(airportRepository).save(any(Airport.class));

    }

    @Test
    @DisplayName("update airport by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(airportRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> airportService.updateById(2, airport));

        verify(airportRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create airport by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedAirport() {

        var persistedAirport = Airport.builder()
                .id(2)
                .name("Guan")
                .city("Chingo")
                .country("China")
                .build();

        doReturn(Optional.of(persistedAirport)).when(airportRepository).findById(anyInt());

        persistedAirport.setName(airport.getName());
        persistedAirport.setCity(airport.getCity());
        persistedAirport.setCountry(airport.getCountry());

        doNothing().when(airportMapper).updateAirportPartial(any(Airport.class), any(Airport.class));

        doReturn(persistedAirport).when(airportRepository).save(any(Airport.class));

        var updatedAirport = airportService.updateOrCreateById(persistedAirport.getId(), airport);

        assertAll(
                () -> assertThat(updatedAirport.getName()).isEqualTo(airport.getName()),
                () -> assertThat(updatedAirport.getCity()).isEqualTo(airport.getCity()),
                () -> assertThat(updatedAirport.getCountry()).isEqualTo(airport.getCountry())
        );

        verify(airportRepository).findById(anyInt());
        verify(airportMapper).updateAirportPartial(any(Airport.class), any(Airport.class));
        verify(airportRepository).save(any(Airport.class));

    }

    @Test
    @DisplayName("update or create airport by not existing id")
    void wheUpdateOrCreateById_thenCreateNewAirport() {

        doReturn(Optional.empty()).when(airportRepository).findById(anyInt());

        doReturn(airport).when(airportRepository).save(any(Airport.class));

        var createdAirport = airportService.updateOrCreateById(2, airport);

        assertAll(
                () -> assertThat(createdAirport).isNotNull(),
                () -> assertThat(createdAirport).isEqualTo(airport)
        );

        verify(airportRepository).findById(anyInt());
        verify(airportRepository).save(any(Airport.class));

    }

}