package com.dmytrobozhor.airlinereservationservice.unit.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Passenger Repository Tests")
@DataJpaTest
class PassengerRepositoryTests {

    @Autowired
    private PassengerRepository passengerRepository;

    private Passenger passenger;

    @BeforeEach
    void setUp() {

        passenger = Passenger
                .builder()
                .firstName("Bile")
                .lastName("Harrington")
                .phoneNumber("7452186394")
                .build();

    }

    @Test
    @DisplayName("save passenger")
    void whenSavePassenger_thenReturnPassengerWithId() {

        var savedPassenger = passengerRepository.save(passenger);

        assertAll(
                () -> assertThat(savedPassenger.getId()).isNotNull(),
                () -> assertThat(savedPassenger).isEqualTo(passenger)
        );

    }

    @Test
    @DisplayName("find passenger by existing id")
    void whenFindById_thenReturnPassenger() {

        var savedPassenger = passengerRepository.save(passenger);

        var passengerOptional = passengerRepository.findById(savedPassenger.getId());

        assertAll(
                () -> assertThat(passengerOptional).isPresent(),
                () -> assertThat(passengerOptional.get()).isEqualTo(savedPassenger)
        );

    }

    @Test
    @DisplayName("find passenger by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var passengerOptional = passengerRepository.findById(1);

        assertThat(passengerOptional).isEmpty();

    }

    @Test
    @DisplayName("find all passengers")
    void whenFindAll_thenReturnAllPassengers() {

        var passengers = Collections.singletonList(passenger);

        passengerRepository.saveAll(passengers);

        var savedPassengers = passengerRepository.findAll();

        assertAll(
                () -> assertThat(savedPassengers).isNotEmpty(),
                () -> assertThat(savedPassengers).hasSameSizeAs(passengers),
                () -> assertThat(savedPassengers).isEqualTo(passengers)
        );

    }

    @Test
    @DisplayName("save all passengers")
    void whenSaveAll_thenReturnSavedPassengers() {

        var passengers = Collections.singletonList(passenger);

        var savedPassengers = passengerRepository.saveAll(passengers);

        assertAll(
                () -> assertThat(savedPassengers).isNotEmpty(),
                () -> assertThat(savedPassengers).hasSameSizeAs(passengers),
                () -> assertThat(savedPassengers).isEqualTo(passengers)
        );

    }

    @Test
    @DisplayName("delete passenger by id")
    void whenDeleteById_thenReturnNothing() {

        var savedPassenger = passengerRepository.save(passenger);

        passengerRepository.deleteById(savedPassenger.getId());

        var passengerOptional = passengerRepository.findById(savedPassenger.getId());

        assertThat(passengerOptional).isEmpty();

    }

    @Test
    @DisplayName("delete passenger")
    void whenDelete_thenReturnNothing() {

        var savedPassenger = passengerRepository.save(passenger);

        passengerRepository.delete(savedPassenger);

        var passengerOptional = passengerRepository.findById(savedPassenger.getId());

        assertThat(passengerOptional).isEmpty();

    }

}