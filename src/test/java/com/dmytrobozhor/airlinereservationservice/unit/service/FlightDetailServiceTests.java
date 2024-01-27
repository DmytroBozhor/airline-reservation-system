package com.dmytrobozhor.airlinereservationservice.unit.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import com.dmytrobozhor.airlinereservationservice.service.FlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("FlightDetail Service Tests")
@ExtendWith(MockitoExtension.class)
class FlightDetailServiceTests {

    @InjectMocks
    private FlightDetailService flightDetailService;

    @Mock
    private FlightDetailRepository flightDetailRepository;

    @Mock
    private FlightDetailMapper flightDetailMapper;

    private FlightDetail flightDetail;

    @BeforeEach
    void setUp() {

        flightDetail = FlightDetail
                .builder()
                .id(1)
                .departureDateTime(Timestamp.valueOf("2020-09-24 16:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2020-09-25 12:30:00"))
                .airplaneType(AirplaneType.BOEING_747)
                .build();

    }

    @Test
    @DisplayName("save flight detail")
    void whenSave_thenReturnSavedFlightDetail() {

        doReturn(flightDetail).when(flightDetailRepository).save(any(FlightDetail.class));

        var savedFlightDetail = flightDetailService.save(flightDetail);

        assertAll(
                () -> assertThat(savedFlightDetail).isNotNull(),
                () -> assertThat(savedFlightDetail).isEqualTo(flightDetail)
        );

        verify(flightDetailRepository).save(any(FlightDetail.class));

    }

    @Test
    @DisplayName("delete flight detail by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(flightDetailRepository.findById(anyInt()))
                .thenReturn(Optional.of(flightDetail))
                .thenReturn(Optional.empty());

        doNothing().when(flightDetailRepository).delete(any(FlightDetail.class));

        flightDetailService.deleteById(flightDetail.getId());

        assertThrows(EntityNotFoundException.class, () -> flightDetailService.findById(flightDetail.getId()));

        verify(flightDetailRepository, times(2)).findById(anyInt());
        verify(flightDetailRepository).delete(any(FlightDetail.class));

    }

    @Test
    @DisplayName("delete flight detail by not existing id")
    void whenDeleteById_thenThrowException() {

        when(flightDetailRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> flightDetailService.deleteById(flightDetail.getId()));

        verify(flightDetailRepository).findById(anyInt());
        verify(flightDetailRepository, never()).delete(any(FlightDetail.class));

    }

    @Test
    @DisplayName("find flight detail by existing id")
    void whenFindById_thenReturnFlightDetail() {

        doReturn(Optional.of(flightDetail)).when(flightDetailRepository).findById(anyInt());

        var savedFlightDetail = flightDetailService.findById(flightDetail.getId());

        assertThat(savedFlightDetail).isNotNull();

        verify(flightDetailRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find flight detail by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(flightDetailRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> flightDetailService.findById(flightDetail.getId()));

        verify(flightDetailRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update flight detail by existing id")
    void whenUpdateById_thenReturnUpdatedFlightDetail() {

        var persistedFlightDetail = FlightDetail.builder()
                .id(2)
                .departureDateTime(Timestamp.valueOf("2020-09-26 17:00:00"))
                .arrivalDateTime(Timestamp.valueOf("2020-09-27 11:30:00"))
                .airplaneType(AirplaneType.AIRBUS_A380)
                .build();

        doReturn(Optional.of(persistedFlightDetail)).when(flightDetailRepository).findById(anyInt());

        persistedFlightDetail.setDepartureDateTime(flightDetail.getDepartureDateTime());
        persistedFlightDetail.setArrivalDateTime(flightDetail.getArrivalDateTime());
        persistedFlightDetail.setAirplaneType(flightDetail.getAirplaneType());

        doNothing().when(flightDetailMapper).updateFlightDetailPartial(any(FlightDetail.class), any(FlightDetail.class));

        doReturn(persistedFlightDetail).when(flightDetailRepository).save(any(FlightDetail.class));

        var updatedFlightDetail = flightDetailService.updateById(persistedFlightDetail.getId(), flightDetail);

        assertAll(
                () -> assertThat(updatedFlightDetail.getDepartureDateTime()).isEqualTo(flightDetail.getDepartureDateTime()),
                () -> assertThat(updatedFlightDetail.getArrivalDateTime()).isEqualTo(flightDetail.getArrivalDateTime()),
                () -> assertThat(updatedFlightDetail.getAirplaneType()).isEqualTo(flightDetail.getAirplaneType())
        );

        verify(flightDetailRepository).findById(anyInt());
        verify(flightDetailMapper).updateFlightDetailPartial(any(FlightDetail.class), any(FlightDetail.class));
        verify(flightDetailRepository).save(any(FlightDetail.class));

    }

    @Test
    @DisplayName("update flight detail by not existing id")
    void whenUpdateById_thenThrowException() {
        doReturn(Optional.empty()).when(flightDetailRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> flightDetailService.updateById(2, flightDetail));

        verify(flightDetailRepository).findById(anyInt());
    }

    @Test
    @DisplayName("update or create flight detail by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedFlightDetail() {

        var persistedFlightDetail = FlightDetail.builder()
                .id(2)
                .departureDateTime(Timestamp.valueOf("2020-09-26 17:00:00"))
                .arrivalDateTime(Timestamp.valueOf("2020-09-27 11:30:00"))
                .airplaneType(AirplaneType.AIRBUS_A380)
                .build();

        doReturn(Optional.of(persistedFlightDetail)).when(flightDetailRepository).findById(anyInt());

        persistedFlightDetail.setDepartureDateTime(flightDetail.getDepartureDateTime());
        persistedFlightDetail.setArrivalDateTime(flightDetail.getArrivalDateTime());
        persistedFlightDetail.setAirplaneType(flightDetail.getAirplaneType());

        doNothing().when(flightDetailMapper).updateFlightDetailPartial(any(FlightDetail.class), any(FlightDetail.class));

        doReturn(persistedFlightDetail).when(flightDetailRepository).save(any(FlightDetail.class));

        var updatedFlightDetail = flightDetailService.updateOrCreateById(persistedFlightDetail.getId(), flightDetail);

        assertAll(
                () -> assertThat(updatedFlightDetail.getDepartureDateTime()).isEqualTo(flightDetail.getDepartureDateTime()),
                () -> assertThat(updatedFlightDetail.getArrivalDateTime()).isEqualTo(flightDetail.getArrivalDateTime()),
                () -> assertThat(updatedFlightDetail.getAirplaneType()).isEqualTo(flightDetail.getAirplaneType())
        );

        verify(flightDetailRepository).findById(anyInt());
        verify(flightDetailMapper).updateFlightDetailPartial(any(FlightDetail.class), any(FlightDetail.class));
        verify(flightDetailRepository).save(any(FlightDetail.class));

    }

    @Test
    @DisplayName("update or create flight detail by not existing id")
    void wheUpdateOrCreateById_thenCreateNewFlightDetail() {

        doReturn(Optional.empty()).when(flightDetailRepository).findById(anyInt());

        doReturn(flightDetail).when(flightDetailRepository).save(any(FlightDetail.class));

        var createdFlightDetail = flightDetailService.updateOrCreateById(2, flightDetail);

        assertAll(
                () -> assertThat(createdFlightDetail).isNotNull(),
                () -> assertThat(createdFlightDetail).isEqualTo(flightDetail)
        );

        verify(flightDetailRepository).findById(anyInt());
        verify(flightDetailRepository).save(any(FlightDetail.class));

    }

    @Test
    @DisplayName("save all flight details")
    void whenSaveAll_thenReturnSavedFlightDetails() {

        var flightDetailsForSave = Collections.singletonList(flightDetail);

        doReturn(flightDetailsForSave).when(flightDetailRepository).saveAll(anyCollection());

        var savedFlightDetails = flightDetailService.saveAll(flightDetailsForSave);

        assertAll(
                () -> assertThat(savedFlightDetails).isNotEmpty(),
                () -> assertThat(savedFlightDetails).hasSameSizeAs(flightDetailsForSave)
        );

        verify(flightDetailRepository).saveAll(anyCollection());

    }

    @Test
    @DisplayName("find all flight details")
    void whenFindAll_thenReturnAllAirports() {

        var flightDetails = Collections.singletonList(flightDetail);

        doReturn(flightDetails).when(flightDetailRepository).findAll();

        var savedFlightDetails = flightDetailService.findAll();

        assertAll(
                () -> assertThat(savedFlightDetails).isNotEmpty(),
                () -> assertThat(savedFlightDetails).hasSameSizeAs(flightDetails)
        );

        verify(flightDetailRepository).findAll();

    }


}
