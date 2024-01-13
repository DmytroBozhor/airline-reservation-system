package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Flight Detail Repository Tests")
@DataJpaTest
class FlightDetailRepositoryTests {

    @Autowired
    private FlightDetailRepository flightDetailRepository;

    @Autowired
    private AirportRepository airportRepository;

    private FlightDetail flightDetail;

    @BeforeEach
    void setUp() {

        Airport sourceAirport = Airport
                .builder()
                .name("National Airport of Molvania")
                .city("Goong")
                .country("Molvania")
                .build();

        Airport destinationAirport = Airport
                .builder()
                .name("Lamba")
                .city("Gon Kong")
                .country("Hovland")
                .build();

        airportRepository.save(sourceAirport);
        airportRepository.save(destinationAirport);

        flightDetail = FlightDetail
                .builder()
                .departureDateTime(Timestamp.valueOf("2020-08-09 21:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2020-08-10 09:30:00"))
                .airplaneType(AirplaneType.BOEING_747)
                .sourceAirport(sourceAirport)
                .destinationAirport(destinationAirport)
                .build();

    }

    @Test
    @DisplayName("save flight detail successfully")
    void whenSaveFlightDetail_thenReturnSavedEntityWithAssignedId() {

        var savedFlightDetail = flightDetailRepository.save(flightDetail);

        assertAll(
                () -> assertThat(savedFlightDetail).isEqualTo(flightDetail),
                () -> assertThat(savedFlightDetail.getId()).isNotNull()
        );

    }

    @Test
    @DisplayName("find flight detail by existing id")
    void whenFindById_thenReturnFoundFlightDetail() {

        var savedFlightDetail = flightDetailRepository.save(flightDetail);

        var flightDetailOptional = flightDetailRepository.findById(savedFlightDetail.getId());

        assertAll(
                () -> assertThat(flightDetailOptional).isPresent(),
                () -> assertThat(flightDetailOptional.get()).isEqualTo(savedFlightDetail)
        );

    }

    @Test
    @DisplayName("find flight detail by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var flightDetailOptional = flightDetailRepository.findById(1);

        assertThat(flightDetailOptional).isEmpty();

    }

    @Test
    @DisplayName("find all flight details successfully")
    void whenFindAll_thenReturnAllFlightDetails() {

        var flightDetails = Collections.singletonList(flightDetail);

        flightDetailRepository.saveAll(flightDetails);

        var savedFlightDetails = flightDetailRepository.findAll();

        assertAll(
                () -> assertThat(savedFlightDetails).isNotEmpty(),
                () -> assertThat(savedFlightDetails).hasSameSizeAs(flightDetails),
                () -> assertThat(savedFlightDetails).isEqualTo(flightDetails)
        );

    }

    @Test
    @DisplayName("whenDeleteById_thenReturnNothing")
    void whenDeleteById_thenReturnNothing() {

        var savedFlightDetail = flightDetailRepository.save(flightDetail);

        flightDetailRepository.deleteById(savedFlightDetail.getId());

        var flightDetailOptional = flightDetailRepository.findById(savedFlightDetail.getId());

        assertThat(flightDetailOptional).isEmpty();

    }

    @Test
    @DisplayName("whenDeleteFlightDetail_thenReturnNothing")
    void whenDeleteFlightDetail_thenReturnNothing() {

        var savedFLightDetail = flightDetailRepository.save(flightDetail);

        flightDetailRepository.delete(savedFLightDetail);

        var flightDetailOptional = flightDetailRepository.findById(savedFLightDetail.getId());

        assertThat(flightDetailOptional).isEmpty();

    }

    @Test
    @DisplayName("whenSaveAll_thenReturnSavedFlightDetails")
    void whenSaveAll_thenReturnSavedFlightDetails() {

        var flightDetails = Collections.singletonList(flightDetail);

        var savedFlightDetails = flightDetailRepository.saveAll(flightDetails);

        assertAll(
                () -> assertThat(savedFlightDetails).isNotEmpty(),
                () -> assertThat(savedFlightDetails).hasSameSizeAs(flightDetails),
                () -> assertThat(savedFlightDetails).isEqualTo(flightDetails)
        );

    }
}