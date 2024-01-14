package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.SeatDetailRepository;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import com.dmytrobozhor.airlinereservationservice.util.mappers.SeatDetailMapper;
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
@DisplayName("SeatDetail Service Tests")
@ExtendWith(MockitoExtension.class)
class SeatDetailServiceTests {

    @InjectMocks
    private SeatDetailService seatDetailService;

    @Mock
    private SeatDetailRepository seatDetailRepository;

    @Mock
    private SeatDetailMapper seatDetailMapper;

    private SeatDetail seatDetail;

    @BeforeEach
    void setUp() {

        seatDetail = SeatDetail
                .builder()
                .id(1)
                .travelClass(TravelClass.builder().name(TravelClassName.FIRST_CLASS).build())
                .flightDetail(FlightDetail.builder().airplaneType(AirplaneType.BOEING_747).build())
                .build();

    }

    @Test
    @DisplayName("save all seat details")
    void whenSaveAll_thenReturnSavedSeatDetails() {

        var seatDetailsForSave = Collections.singletonList(seatDetail);

        doReturn(seatDetailsForSave).when(seatDetailRepository).saveAll(any());

        var savedSeatDetails = seatDetailService.saveAll(seatDetailsForSave);

        assertAll(
                () -> assertThat(savedSeatDetails).isNotEmpty(),
                () -> assertThat(savedSeatDetails).hasSameSizeAs(seatDetailsForSave)
        );

        verify(seatDetailRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all seat details")
    void whenFindAll_thenReturnAllSeatDetails() {

        var seatDetails = Collections.singletonList(seatDetail);

        doReturn(seatDetails).when(seatDetailRepository).findAll();

        var savedSeatDetails = seatDetailService.findAll();

        assertAll(
                () -> assertThat(savedSeatDetails).isNotEmpty(),
                () -> assertThat(savedSeatDetails).hasSameSizeAs(seatDetails)
        );

        verify(seatDetailRepository).findAll();

    }

    @Test
    @DisplayName("save seat detail")
    void whenSave_thenReturnSavedSeatDetail() {

        doReturn(seatDetail).when(seatDetailRepository).save(any());

        var savedSeatDetail = seatDetailService.save(seatDetail);

        assertAll(
                () -> assertThat(savedSeatDetail).isNotNull(),
                () -> assertThat(savedSeatDetail).isEqualTo(seatDetail)
        );

        verify(seatDetailRepository).save(any());

    }

    @Test
    @DisplayName("delete seat detail by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(seatDetailRepository.findById(anyInt()))
                .thenReturn(Optional.of(seatDetail))
                .thenReturn(Optional.empty());

        doNothing().when(seatDetailRepository).delete(any());

        seatDetailService.deleteById(seatDetail.getId());

        assertThrows(EntityNotFoundException.class, () -> seatDetailService.findById(seatDetail.getId()));

        verify(seatDetailRepository, times(2)).findById(anyInt());
        verify(seatDetailRepository).delete(any());

    }

    @Test
    @DisplayName("delete seat detail by not existing id")
    void whenDeleteById_thenThrowException() {

        when(seatDetailRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> seatDetailService.deleteById(seatDetail.getId()));

        verify(seatDetailRepository).findById(anyInt());
        verify(seatDetailRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find seat detail by existing id")
    void whenFindById_thenReturnSeatDetail() {

        doReturn(Optional.of(seatDetail)).when(seatDetailRepository).findById(anyInt());

        var savedSeatDetail = seatDetailService.findById(seatDetail.getId());

        assertThat(savedSeatDetail).isNotNull();

        verify(seatDetailRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find seat detail by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(seatDetailRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> seatDetailService.findById(seatDetail.getId()));

        verify(seatDetailRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update seat detail by existing id")
    void whenUpdateById_thenReturnUpdatedSeatDetail() {

        var persistedSeatDetail = SeatDetail.builder()
                .id(2)
                .travelClass(TravelClass.builder().name(TravelClassName.ECONOMY_CLASS).build())
                .flightDetail(FlightDetail.builder().airplaneType(AirplaneType.AIRBUS_A380).build())
                .build();

        doReturn(Optional.of(persistedSeatDetail)).when(seatDetailRepository).findById(anyInt());

        persistedSeatDetail.setTravelClass(seatDetail.getTravelClass());
        persistedSeatDetail.setFlightDetail(seatDetail.getFlightDetail());

        doNothing().when(seatDetailMapper).updateSeatDetailPartial(any(), any());

        doReturn(persistedSeatDetail).when(seatDetailRepository).save(any());

        var updatedSeatDetail = seatDetailService.updateById(persistedSeatDetail.getId(), seatDetail);

        assertAll(
                () -> assertThat(updatedSeatDetail.getTravelClass()).isEqualTo(seatDetail.getTravelClass()),
                () -> assertThat(updatedSeatDetail.getFlightDetail()).isEqualTo(seatDetail.getFlightDetail())
        );

        verify(seatDetailRepository).findById(anyInt());
        verify(seatDetailMapper).updateSeatDetailPartial(any(), any());
        verify(seatDetailRepository).save(any());
    }

    @Test
    @DisplayName("update seat detail by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(seatDetailRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> seatDetailService.updateById(2, seatDetail));

        verify(seatDetailRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create seat detail by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedSeatDetail() {

        var persistedSeatDetail = SeatDetail.builder()
                .id(2)
                .travelClass(TravelClass.builder().name(TravelClassName.ECONOMY_CLASS).build())
                .flightDetail(FlightDetail.builder().airplaneType(AirplaneType.AIRBUS_A380).build())
                .build();

        doReturn(Optional.of(persistedSeatDetail)).when(seatDetailRepository).findById(anyInt());

        persistedSeatDetail.setTravelClass(seatDetail.getTravelClass());
        persistedSeatDetail.setFlightDetail(seatDetail.getFlightDetail());

        doNothing().when(seatDetailMapper).updateSeatDetailPartial(any(), any());

        doReturn(persistedSeatDetail).when(seatDetailRepository).save(any());

        var updatedSeatDetail = seatDetailService.updateOrCreateById(persistedSeatDetail.getId(), seatDetail);

        assertAll(
                () -> assertThat(updatedSeatDetail.getTravelClass()).isEqualTo(seatDetail.getTravelClass()),
                () -> assertThat(updatedSeatDetail.getFlightDetail()).isEqualTo(seatDetail.getFlightDetail())
        );

        verify(seatDetailRepository).findById(anyInt());
        verify(seatDetailMapper).updateSeatDetailPartial(any(), any());
        verify(seatDetailRepository).save(any());

    }

    @Test
    @DisplayName("update or create seat detail by not existing id")
    void wheUpdateOrCreateById_thenCreateNewSeatDetail() {

        doReturn(Optional.empty()).when(seatDetailRepository).findById(anyInt());

        doReturn(seatDetail).when(seatDetailRepository).save(any());

        var createdSeatDetail = seatDetailService.updateOrCreateById(2, seatDetail);

        assertAll(
                () -> assertThat(createdSeatDetail).isNotNull(),
                () -> assertThat(createdSeatDetail).isEqualTo(seatDetail)
        );

        verify(seatDetailRepository).findById(anyInt());
        verify(seatDetailRepository).save(any());

    }

}
