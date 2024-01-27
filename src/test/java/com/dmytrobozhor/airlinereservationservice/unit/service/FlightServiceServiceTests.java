package com.dmytrobozhor.airlinereservationservice.unit.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.repository.FlightServiceRepository;
import com.dmytrobozhor.airlinereservationservice.service.FlightServiceService;
import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightServiceMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("FlightService Service Tests")
@ExtendWith(MockitoExtension.class)
class FlightServiceServiceTests {

    @InjectMocks
    private FlightServiceService flightServiceService;

    @Mock
    private FlightServiceRepository flightServiceRepository;

    @Mock
    private FlightServiceMapper flightServiceMapper;

    private FlightService flightService;

    @BeforeEach
    void setUp() {

        flightService = FlightService
                .builder()
                .id(1)
                .serviceName(ServiceName.FOOD)
                .build();

    }

    @Test
    @DisplayName("save all flight services")
    void whenSaveAll_thenReturnSavedFlightServices() {

        var flightServicesForSave = Collections.singletonList(flightService);

        doReturn(flightServicesForSave).when(flightServiceRepository).saveAll(any());

        var savedFlightServices = flightServiceService.saveAll(flightServicesForSave);

        assertAll(
                () -> assertThat(savedFlightServices).isNotEmpty(),
                () -> assertThat(savedFlightServices).hasSameSizeAs(flightServicesForSave)
        );

        verify(flightServiceRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all flight services")
    void whenFindAll_thenReturnAllFlightServices() {

        var flightServices = Collections.singletonList(flightService);

        doReturn(flightServices).when(flightServiceRepository).findAll();

        var savedFlightServices = flightServiceService.findAll();

        assertAll(
                () -> assertThat(savedFlightServices).isNotEmpty(),
                () -> assertThat(savedFlightServices).hasSameSizeAs(flightServices)
        );

        verify(flightServiceRepository).findAll();

    }

    @Test
    @DisplayName("save flight service")
    void whenSave_thenReturnSavedFlightService() {

        doReturn(flightService).when(flightServiceRepository).save(any());

        var savedFlightService = flightServiceService.save(flightService);

        assertAll(
                () -> assertThat(savedFlightService).isNotNull(),
                () -> assertThat(savedFlightService).isEqualTo(flightService)
        );

        verify(flightServiceRepository).save(any());

    }

    @Test
    @DisplayName("delete flight service by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(flightServiceRepository.findById(anyInt()))
                .thenReturn(Optional.of(flightService))
                .thenReturn(Optional.empty());

        doNothing().when(flightServiceRepository).delete(any());

        flightServiceService.deleteById(flightService.getId());

        assertThrows(EntityNotFoundException.class, () -> flightServiceService.findById(flightService.getId()));

        verify(flightServiceRepository, times(2)).findById(anyInt());
        verify(flightServiceRepository).delete(any());

    }

    @Test
    @DisplayName("delete flight service by not existing id")
    void whenDeleteById_thenThrowException() {

        when(flightServiceRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> flightServiceService.deleteById(flightService.getId()));

        verify(flightServiceRepository).findById(anyInt());
        verify(flightServiceRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find flight service by existing id")
    void whenFindById_thenReturnFlightService() {

        doReturn(Optional.of(flightService)).when(flightServiceRepository).findById(anyInt());

        var savedFlightService = flightServiceService.findById(flightService.getId());

        assertThat(savedFlightService).isNotNull();

        verify(flightServiceRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find flight service by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(flightServiceRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> flightServiceService.findById(flightService.getId()));

        verify(flightServiceRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update flight service by existing id")
    void whenUpdateById_thenReturnUpdatedFlightService() {

        var persistedFlightService = FlightService
                .builder()
                .id(2)
                .serviceName(ServiceName.LOUNGE)
                .build();

        doReturn(Optional.of(persistedFlightService)).when(flightServiceRepository).findById(anyInt());

        persistedFlightService.setServiceName(flightService.getServiceName());

        doNothing().when(flightServiceMapper).updateFlightServicePartial(any(), any());

        doReturn(persistedFlightService).when(flightServiceRepository).save(any());

        var updatedFlightService = flightServiceService.updateById(persistedFlightService.getId(), flightService);

        assertAll(
                () -> assertThat(updatedFlightService.getServiceName()).isEqualTo(flightService.getServiceName())
        );

        verify(flightServiceRepository).findById(anyInt());
        verify(flightServiceMapper).updateFlightServicePartial(any(), any());
        verify(flightServiceRepository).save(any());

    }

    @Test
    @DisplayName("update flight service by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(flightServiceRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> flightServiceService.updateById(2, flightService));

        verify(flightServiceRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create flight service by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedFlightService() {

        var persistedFlightService = FlightService
                .builder()
                .id(2)
                .serviceName(ServiceName.LOUNGE)
                .build();

        doReturn(Optional.of(persistedFlightService)).when(flightServiceRepository).findById(anyInt());

        persistedFlightService.setServiceName(flightService.getServiceName());

        doNothing().when(flightServiceMapper).updateFlightServicePartial(any(), any());

        doReturn(persistedFlightService).when(flightServiceRepository).save(any());

        var updatedFlightService = flightServiceService.updateOrCreateById(persistedFlightService.getId(), flightService);

        assertAll(
                () -> assertThat(updatedFlightService.getServiceName()).isEqualTo(flightService.getServiceName())
        );

        verify(flightServiceRepository).findById(anyInt());
        verify(flightServiceMapper).updateFlightServicePartial(any(), any());
        verify(flightServiceRepository).save(any());

    }

    @Test
    @DisplayName("update or create flight service by not existing id")
    void wheUpdateOrCreateById_thenCreateNewFlightService() {

        doReturn(Optional.empty()).when(flightServiceRepository).findById(anyInt());

        doReturn(flightService).when(flightServiceRepository).save(any());

        var createdFlightService = flightServiceService.updateOrCreateById(2, flightService);

        assertAll(
                () -> assertThat(createdFlightService).isNotNull(),
                () -> assertThat(createdFlightService).isEqualTo(flightService)
        );

        verify(flightServiceRepository).findById(anyInt());
        verify(flightServiceRepository).save(any());

    }
}
