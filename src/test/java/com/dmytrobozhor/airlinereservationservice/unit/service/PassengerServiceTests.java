package com.dmytrobozhor.airlinereservationservice.unit.service;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.repository.PassengerRepository;
import com.dmytrobozhor.airlinereservationservice.service.PassengerService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PassengerMapper;
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
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("Passenger Service Tests")
@ExtendWith(MockitoExtension.class)
class PassengerServiceTests {

    @InjectMocks
    private PassengerService passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PassengerMapper passengerMapper;

    private Passenger passenger;

    @BeforeEach
    void setUp() {

        passenger = Passenger
                .builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .build();

    }

    @Test
    @DisplayName("save all passengers")
    void whenSaveAll_thenReturnSavedPassengers() {

        var passengersForSave = Collections.singletonList(passenger);

        doReturn(passengersForSave).when(passengerRepository).saveAll(anyCollection());

        var savedPassengers = passengerService.saveAll(passengersForSave);

        assertAll(
                () -> assertThat(savedPassengers).isNotEmpty(),
                () -> assertThat(savedPassengers).hasSameSizeAs(passengersForSave)
        );

        verify(passengerRepository).saveAll(anyCollection());

    }

    @Test
    @DisplayName("find all passengers")
    void whenFindAll_thenReturnAllPassengers() {

        var passengers = Collections.singletonList(passenger);

        doReturn(passengers).when(passengerRepository).findAll();

        var savedPassengers = passengerService.findAll();

        assertAll(
                () -> assertThat(savedPassengers).isNotEmpty(),
                () -> assertThat(savedPassengers).hasSameSizeAs(passengers)
        );

        verify(passengerRepository).findAll();

    }

    @Test
    @DisplayName("save passenger")
    void whenSave_thenReturnSavedPassenger() {

        doReturn(passenger).when(passengerRepository).save(any(Passenger.class));

        var savedPassenger = passengerService.save(passenger);

        assertAll(
                () -> assertThat(savedPassenger).isNotNull(),
                () -> assertThat(savedPassenger).isEqualTo(passenger)
        );

        verify(passengerRepository).save(any(Passenger.class));

    }

    @Test
    @DisplayName("delete passenger by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(passengerRepository.findById(anyInt()))
                .thenReturn(Optional.of(passenger))
                .thenReturn(Optional.empty());

        doNothing().when(passengerRepository).delete(any(Passenger.class));

        passengerService.deleteById(passenger.getId());

        assertThrows(EntityNotFoundException.class, () -> passengerService.findById(passenger.getId()));

        verify(passengerRepository, times(2)).findById(anyInt());
        verify(passengerRepository).delete(any(Passenger.class));

    }

    @Test
    @DisplayName("delete passenger by not existing id")
    void whenDeleteById_thenThrowException() {

        when(passengerRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> passengerService.deleteById(passenger.getId()));

        verify(passengerRepository).findById(anyInt());
        verify(passengerRepository, never()).delete(any(Passenger.class));

    }

    @Test
    @DisplayName("find passenger by existing id")
    void whenFindById_thenReturnPassenger() {

        doReturn(Optional.of(passenger)).when(passengerRepository).findById(anyInt());

        var savedPassenger = passengerService.findById(passenger.getId());

        assertThat(savedPassenger).isNotNull();

        verify(passengerRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find passenger by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(passengerRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> passengerService.findById(passenger.getId()));

        verify(passengerRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update passenger by existing id")
    void whenUpdateById_thenReturnUpdatedPassenger() {

        var persistedPassenger = Passenger.builder()
                .id(2)
                .firstName("Jane")
                .lastName("Doe")
                .build();

        doReturn(Optional.of(persistedPassenger)).when(passengerRepository).findById(anyInt());

        persistedPassenger.setFirstName(passenger.getFirstName());
        persistedPassenger.setLastName(passenger.getLastName());

        doNothing().when(passengerMapper).updatePassengerPartial(any(Passenger.class), any(Passenger.class));

        doReturn(persistedPassenger).when(passengerRepository).save(any(Passenger.class));

        var updatedPassenger = passengerService.updateById(persistedPassenger.getId(), passenger);

        assertAll(
                () -> assertThat(updatedPassenger.getFirstName()).isEqualTo(passenger.getFirstName()),
                () -> assertThat(updatedPassenger.getLastName()).isEqualTo(passenger.getLastName())
        );

        verify(passengerRepository).findById(anyInt());
        verify(passengerMapper).updatePassengerPartial(any(Passenger.class), any(Passenger.class));
        verify(passengerRepository).save(any(Passenger.class));

    }

    @Test
    @DisplayName("update passenger by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(passengerRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> passengerService.updateById(2, passenger));

        verify(passengerRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create passenger by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedPassenger() {

        var persistedPassenger = Passenger.builder()
                .id(2)
                .firstName("Jane")
                .lastName("Doe")
                .build();

        doReturn(Optional.of(persistedPassenger)).when(passengerRepository).findById(anyInt());

        persistedPassenger.setFirstName(passenger.getFirstName());
        persistedPassenger.setLastName(passenger.getLastName());

        doNothing().when(passengerMapper).updatePassengerPartial(any(Passenger.class), any(Passenger.class));

        doReturn(persistedPassenger).when(passengerRepository).save(any(Passenger.class));

        var updatedPassenger = passengerService.updateOrCreateById(persistedPassenger.getId(), passenger);

        assertAll(
                () -> assertThat(updatedPassenger.getFirstName()).isEqualTo(passenger.getFirstName()),
                () -> assertThat(updatedPassenger.getLastName()).isEqualTo(passenger.getLastName())
        );

        verify(passengerRepository).findById(anyInt());
        verify(passengerMapper).updatePassengerPartial(any(Passenger.class), any(Passenger.class));
        verify(passengerRepository).save(any(Passenger.class));

    }

    @Test
    @DisplayName("update or create passenger by not existing id")
    void wheUpdateOrCreateById_thenCreateNewPassenger() {

        doReturn(Optional.empty()).when(passengerRepository).findById(anyInt());

        doReturn(passenger).when(passengerRepository).save(any(Passenger.class));

        var createdPassenger = passengerService.updateOrCreateById(2, passenger);

        assertAll(
                () -> assertThat(createdPassenger).isNotNull(),
                () -> assertThat(createdPassenger).isEqualTo(passenger)
        );

        verify(passengerRepository).findById(anyInt());
        verify(passengerRepository).save(any(Passenger.class));

    }

}
