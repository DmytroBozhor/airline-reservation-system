package com.dmytrobozhor.airlinereservationservice.unit.service;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import com.dmytrobozhor.airlinereservationservice.domain.FlightCost;
import com.dmytrobozhor.airlinereservationservice.repository.FlightCostRepository;
import com.dmytrobozhor.airlinereservationservice.service.FlightCostService;
import com.dmytrobozhor.airlinereservationservice.util.compositeid.FlightCostId;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightCostMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
@DisplayName("FlightCost Service Tests")
@ExtendWith(MockitoExtension.class)

// TODO: illegal argument exception for some reason
@Disabled
class FlightCostServiceTests {

    @InjectMocks
    private FlightCostService flightCostService;

    @Mock
    private FlightCostRepository flightCostRepository;

    @Mock
    private FlightCostMapper flightCostMapper;

    private FlightCost flightCost;

    @BeforeEach
    void setUp() {

        flightCost = FlightCost
                .builder()
                .id(FlightCostId
                        .builder()
                        .seatDetailId(1)
                        .validFromDateId(Date.valueOf("2024-01-01"))
                        .build())
                .validToDate(Calendar
                        .builder()
                        .date(Date.valueOf("24-05-01"))
                        .businessDay(true)
                        .build())
                .cost(BigDecimal.valueOf(120))
                .build();

    }

    @Test
    @DisplayName("save all flight costs")
    void whenSaveAll_thenReturnSavedFlightCosts() {

        var flightCostsForSave = Collections.singletonList(flightCost);

        doReturn(flightCostsForSave).when(flightCostRepository).saveAll(any());

        var savedFlightCosts = flightCostService.saveAll(flightCostsForSave);

        assertAll(
                () -> assertThat(savedFlightCosts).isNotEmpty(),
                () -> assertThat(savedFlightCosts).hasSameSizeAs(flightCostsForSave)
        );

        verify(flightCostRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all flight costs")
    void whenFindAll_thenReturnAllFlightCosts() {

        var flightCosts = Collections.singletonList(flightCost);

        doReturn(flightCosts).when(flightCostRepository).findAll();

        var savedFlightCosts = flightCostService.findAll();

        assertAll(
                () -> assertThat(savedFlightCosts).isNotEmpty(),
                () -> assertThat(savedFlightCosts).hasSameSizeAs(flightCosts)
        );

        verify(flightCostRepository).findAll();

    }

    @Test
    @DisplayName("save flight cost")
    void whenSave_thenReturnSavedFlightCost() {

        doReturn(flightCost).when(flightCostRepository).save(any());

        var savedFlightCost = flightCostService.save(flightCost);

        assertAll(
                () -> assertThat(savedFlightCost).isNotNull(),
                () -> assertThat(savedFlightCost).isEqualTo(flightCost)
        );

        verify(flightCostRepository).save(any());

    }

    @Test
    @DisplayName("delete flight cost by existing id")
    void whenDeleteById_thenReturnNothing() {

        var id = FlightCostId
                .builder()
                .seatDetailId(1)
                .validFromDateId(Date.valueOf("24-04-27"))
                .build();

        when(flightCostRepository.findById(id))
                .thenReturn(Optional.of(flightCost))
                .thenReturn(Optional.empty());

        doNothing().when(flightCostRepository).delete(any());

        flightCostService.deleteById(flightCost.getId());

        assertThrows(EntityNotFoundException.class, () -> flightCostService.findById(flightCost.getId()));

        verify(flightCostRepository, times(2)).findById(any(FlightCostId.class));
        verify(flightCostRepository).delete(any());

    }

    @Test
    @DisplayName("delete flight cost by not existing id")
    void whenDeleteById_thenThrowException() {

        when(flightCostRepository.findById(any(FlightCostId.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> flightCostService.deleteById(flightCost.getId()));

        verify(flightCostRepository).findById(any(FlightCostId.class));
        verify(flightCostRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find flight cost by existing id")
    void whenFindById_thenReturnFlightCost() {

        doReturn(Optional.of(flightCost)).when(flightCostRepository).findById(any(FlightCostId.class));

        var savedFlightCost = flightCostService.findById(flightCost.getId());

        assertThat(savedFlightCost).isNotNull();

        verify(flightCostRepository).findById(any(FlightCostId.class));

    }

    @Test
    @DisplayName("find flight cost by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(flightCostRepository).findById(any(FlightCostId.class));

        assertThrows(EntityNotFoundException.class, () -> flightCostService.findById(flightCost.getId()));

        verify(flightCostRepository).findById(any(FlightCostId.class));

    }

    @Test
    @DisplayName("update flight cost by existing id")
    void whenUpdateById_thenReturnUpdatedFlightCost() {

        var persistedFlightCost = FlightCost
                .builder()
                .id(FlightCostId
                        .builder()
                        .seatDetailId(1)
                        .validFromDateId(Date.valueOf("24-08-04"))
                        .build())
                .validToDate(Calendar
                        .builder()
                        .date(Date.valueOf("24-10-01"))
                        .businessDay(true)
                        .build())
                .cost(BigDecimal.valueOf(140.25D))
                .build();

        doReturn(Optional.of(persistedFlightCost)).when(flightCostRepository).findById(any(FlightCostId.class));

        persistedFlightCost.setValidToDate(flightCost.getValidToDate());
        persistedFlightCost.setCost(flightCost.getCost());

        doNothing().when(flightCostMapper).updateFlightCostPartial(any(), any());

        doReturn(persistedFlightCost).when(flightCostRepository).save(any());

        var updatedFlightCost = flightCostService.updateById(persistedFlightCost.getId(), flightCost);

        assertAll(
                () -> assertThat(updatedFlightCost.getValidToDate()).isEqualTo(flightCost.getValidToDate()),
                () -> assertThat(updatedFlightCost.getCost()).isEqualTo(flightCost.getCost())
        );

        verify(flightCostRepository).findById(any(FlightCostId.class));
        verify(flightCostMapper).updateFlightCostPartial(any(), any());
        verify(flightCostRepository).save(any());

    }

    @Test
    @DisplayName("update flight cost by not existing id")
    void whenUpdateById_thenThrowException() {

        var id = FlightCostId
                .builder()
                .seatDetailId(1)
                .validFromDateId(Date.valueOf("24-05-15"))
                .build();

        doReturn(Optional.empty()).when(flightCostRepository).findById(any(FlightCostId.class));

        assertThrows(EntityNotFoundException.class, () -> flightCostService.updateById(id, flightCost));

        verify(flightCostRepository).findById(any(FlightCostId.class));

    }

    @Test
    @DisplayName("update or create flight cost by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedFlightCost() {

        var persistedFlightCost = FlightCost
                .builder()
                .id(FlightCostId
                        .builder()
                        .seatDetailId(1)
                        .validFromDateId(Date.valueOf("24-08-04"))
                        .build())
                .validToDate(Calendar
                        .builder()
                        .date(Date.valueOf("24-10-01"))
                        .businessDay(true)
                        .build())
                .cost(BigDecimal.valueOf(140.25D))
                .build();

        doReturn(Optional.of(persistedFlightCost)).when(flightCostRepository).findById(any(FlightCostId.class));

        persistedFlightCost.setValidToDate(flightCost.getValidToDate());
        persistedFlightCost.setCost(flightCost.getCost());

        doNothing().when(flightCostMapper).updateFlightCostPartial(any(), any());

        doReturn(persistedFlightCost).when(flightCostRepository).save(any());

        var updatedFlightCost = flightCostService.updateOrCreateById(persistedFlightCost.getId(), flightCost);

        assertAll(
                () -> assertThat(updatedFlightCost.getValidToDate()).isEqualTo(flightCost.getValidToDate()),
                () -> assertThat(updatedFlightCost.getCost()).isEqualTo(flightCost.getCost())
        );

        verify(flightCostRepository).findById(any(FlightCostId.class));
        verify(flightCostMapper).updateFlightCostPartial(any(), any());
        verify(flightCostRepository).save(any());

    }

    @Test
    @DisplayName("update or create flight cost by not existing id")
    void wheUpdateOrCreateById_thenCreateNewFlightCost() {

        var id = FlightCostId
                .builder()
                .seatDetailId(1)
                .validFromDateId(Date.valueOf("24-05-15"))
                .build();

        doReturn(Optional.empty()).when(flightCostRepository).findById(any(FlightCostId.class));

        doReturn(flightCost).when(flightCostRepository).save(any());

        var createdFlightCost = flightCostService.updateOrCreateById(id, flightCost);

        assertAll(
                () -> assertThat(createdFlightCost).isNotNull(),
                () -> assertThat(createdFlightCost).isEqualTo(flightCost)
        );

        verify(flightCostRepository).findById(any(FlightCostId.class));
        verify(flightCostRepository).save(any());

    }
}
