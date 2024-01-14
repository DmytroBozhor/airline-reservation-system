package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import com.dmytrobozhor.airlinereservationservice.repository.ReservationRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ReservationMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("Reservation Service Tests")
@ExtendWith(MockitoExtension.class)
class ReservationServiceTests {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;

    private Reservation reservation;

    @BeforeEach
    void setUp() {

        reservation = Reservation
                .builder()
                .id(1)
                .passenger(Passenger
                        .builder()
                        .firstName("Billy")
                        .lastName("Robinzon")
                        .phoneNumber("1245236325")
                        .build())
                .reservationDateTime(Timestamp.valueOf("2020-09-24 16:30:00"))
                .build();

    }

    @Test
    @DisplayName("save all reservations")
    void whenSaveAll_thenReturnSavedReservations() {

        var reservationsForSave = Collections.singletonList(reservation);

        doReturn(reservationsForSave).when(reservationRepository).saveAll(any());

        var savedReservations = reservationService.saveAll(reservationsForSave);

        assertAll(
                () -> assertThat(savedReservations).isNotEmpty(),
                () -> assertThat(savedReservations).hasSameSizeAs(reservationsForSave)
        );

        verify(reservationRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all reservations")
    void whenFindAll_thenReturnAllReservations() {

        var reservations = Collections.singletonList(reservation);

        doReturn(reservations).when(reservationRepository).findAll();

        var savedReservations = reservationService.findAll();

        assertAll(
                () -> assertThat(savedReservations).isNotEmpty(),
                () -> assertThat(savedReservations).hasSameSizeAs(reservations)
        );

        verify(reservationRepository).findAll();

    }

    @Test
    @DisplayName("save reservation")
    void whenSave_thenReturnSavedReservation() {

        doReturn(reservation).when(reservationRepository).save(any());

        var savedReservation = reservationService.save(reservation);

        assertAll(
                () -> assertThat(savedReservation).isNotNull(),
                () -> assertThat(savedReservation).isEqualTo(reservation)
        );

        verify(reservationRepository).save(any());

    }

    @Test
    @DisplayName("delete reservation by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(reservationRepository.findById(anyInt()))
                .thenReturn(Optional.of(reservation))
                .thenReturn(Optional.empty());

        doNothing().when(reservationRepository).delete(any());

        reservationService.deleteById(reservation.getId());

        assertThrows(EntityNotFoundException.class, () -> reservationService.findById(reservation.getId()));

        verify(reservationRepository, times(2)).findById(anyInt());
        verify(reservationRepository).delete(any());

    }

    @Test
    @DisplayName("delete reservation by not existing id")
    void whenDeleteById_thenThrowException() {

        when(reservationRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reservationService.deleteById(reservation.getId()));

        verify(reservationRepository).findById(anyInt());
        verify(reservationRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find reservation by existing id")
    void whenFindById_thenReturnReservation() {

        doReturn(Optional.of(reservation)).when(reservationRepository).findById(anyInt());

        var savedReservation = reservationService.findById(reservation.getId());

        assertThat(savedReservation).isNotNull();

        verify(reservationRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find reservation by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(reservationRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> reservationService.findById(reservation.getId()));

        verify(reservationRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update reservation by existing id")
    void whenUpdateById_thenReturnUpdatedReservation() {

        var persistedReservation = Reservation.builder()
                .id(1)
                .passenger(Passenger
                        .builder()
                        .firstName("While")
                        .lastName("Packman")
                        .phoneNumber("7593568427")
                        .build())
                .reservationDateTime(Timestamp.valueOf("2020-10-17 11:30:00"))
                .build();

        doReturn(Optional.of(persistedReservation)).when(reservationRepository).findById(anyInt());

        persistedReservation.setPassenger(reservation.getPassenger());
        persistedReservation.setReservationDateTime(reservation.getReservationDateTime());

        doNothing().when(reservationMapper).updateReservationPartial(any(), any());

        doReturn(persistedReservation).when(reservationRepository).save(any());

        var updatedReservation = reservationService.updateById(persistedReservation.getId(), reservation);

        assertAll(
                () -> assertThat(updatedReservation.getPassenger()).isEqualTo(reservation.getPassenger()),
                () -> assertThat(updatedReservation.getReservationDateTime()).isEqualTo(reservation.getReservationDateTime())
        );

        verify(reservationRepository).findById(anyInt());
        verify(reservationMapper).updateReservationPartial(any(), any());
        verify(reservationRepository).save(any());

    }

    @Test
    @DisplayName("update reservation by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(reservationRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> reservationService.updateById(2, reservation));

        verify(reservationRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create reservation by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedReservation() {

        var persistedReservation = Reservation.builder()
                .id(1)
                .passenger(Passenger
                        .builder()
                        .firstName("While")
                        .lastName("Packman")
                        .phoneNumber("7593568427")
                        .build())
                .reservationDateTime(Timestamp.valueOf("2020-10-17 11:30:00"))
                .build();

        doReturn(Optional.of(persistedReservation)).when(reservationRepository).findById(anyInt());

        persistedReservation.setPassenger(reservation.getPassenger());
        persistedReservation.setReservationDateTime(reservation.getReservationDateTime());

        doNothing().when(reservationMapper).updateReservationPartial(any(), any());

        doReturn(persistedReservation).when(reservationRepository).save(any());

        var updatedReservation = reservationService.updateOrCreateById(persistedReservation.getId(), reservation);

        assertAll(
                () -> assertThat(updatedReservation.getPassenger()).isEqualTo(reservation.getPassenger()),
                () -> assertThat(updatedReservation.getReservationDateTime()).isEqualTo(reservation.getReservationDateTime())
        );

        verify(reservationRepository).findById(anyInt());
        verify(reservationMapper).updateReservationPartial(any(), any());
        verify(reservationRepository).save(any());

    }

    @Test
    @DisplayName("update or create reservation by not existing id")
    void wheUpdateOrCreateById_thenCreateNewReservation() {

        doReturn(Optional.empty()).when(reservationRepository).findById(anyInt());

        doReturn(reservation).when(reservationRepository).save(any());

        var createdReservation = reservationService.updateOrCreateById(2, reservation);

        assertAll(
                () -> assertThat(createdReservation).isNotNull(),
                () -> assertThat(createdReservation).isEqualTo(reservation)
        );

        verify(reservationRepository).findById(anyInt());
        verify(reservationRepository).save(any());

    }
}
