package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Flight Service Repository Tests")
@DataJpaTest
class FlightServiceRepositoryTests {

    @Autowired
    private FlightServiceRepository flightServiceRepository;

    private FlightService flightService;

    @BeforeEach
    void setUp() {

        flightService = FlightService.builder()
                .serviceName(ServiceName.WIFI)
                .build();

    }

    @Test
    @DisplayName("save flight service")
    void whenSave_thenReturnSavedFlightServiceWithId() {
        var savedFlightService = flightServiceRepository.save(flightService);

        assertAll(
                () -> assertThat(savedFlightService.getId()).isNotNull(),
                () -> assertThat(savedFlightService).isEqualTo(flightService)
        );
    }

    @Test
    @DisplayName("find flight service by existing id")
    void whenFindById_thenReturnFlightService() {
        var savedFlightService = flightServiceRepository.save(flightService);

        var flightServiceOptional = flightServiceRepository.findById(savedFlightService.getId());

        assertAll(
                () -> assertThat(flightServiceOptional).isPresent(),
                () -> assertThat(flightServiceOptional.get()).isEqualTo(savedFlightService)
        );
    }

    @Test
    @DisplayName("find flight service by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {
        var flightServiceOptional = flightServiceRepository.findById(1);

        assertThat(flightServiceOptional).isEmpty();
    }

    @Test
    @DisplayName("find all flight services")
    void whenFindAll_thenReturnAllFlightServices() {
        var flightServices = Collections.singletonList(flightService);

        flightServiceRepository.saveAll(flightServices);

        var savedFlightServices = flightServiceRepository.findAll();

        assertAll(
                () -> assertThat(savedFlightServices).isNotEmpty(),
                () -> assertThat(savedFlightServices).hasSameSizeAs(flightServices),
                () -> assertThat(savedFlightServices).isEqualTo(flightServices)
        );
    }

    @Test
    @DisplayName("save all flight services")
    void whenSaveAll_thenReturnSavedFlightServices() {
        var flightServices = Collections.singletonList(flightService);

        var savedFlightServices = flightServiceRepository.saveAll(flightServices);

        assertAll(
                () -> assertThat(savedFlightServices).isNotEmpty(),
                () -> assertThat(savedFlightServices).hasSameSizeAs(flightServices),
                () -> assertThat(savedFlightServices).isEqualTo(flightServices)
        );
    }

    @Test
    @DisplayName("delete flight service by id")
    void whenDeleteById_thenReturnNothing() {
        var savedFlightService = flightServiceRepository.save(flightService);

        flightServiceRepository.deleteById(savedFlightService.getId());

        var flightServiceOptional = flightServiceRepository.findById(savedFlightService.getId());

        assertThat(flightServiceOptional).isEmpty();
    }

    @Test
    @DisplayName("delete flight service")
    void whenDelete_thenReturnNothing() {
        var savedFlightService = flightServiceRepository.save(flightService);

        flightServiceRepository.delete(savedFlightService);

        var flightServiceOptional = flightServiceRepository.findById(savedFlightService.getId());

        assertThat(flightServiceOptional).isEmpty();
    }
}
