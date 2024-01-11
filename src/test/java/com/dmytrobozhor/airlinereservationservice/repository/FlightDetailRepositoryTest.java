package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Flight Detail Repository Test")
@DataJpaTest
class FlightDetailRepositoryTest {

    @Autowired
    private FlightDetailRepository flightDetailRepository;

    @Autowired
    private AirportRepository airportRepository;

    private FlightDetail flightDetail;

    @BeforeAll
    static void saveAirports(){

    }

//    TODO: we still can add a flight detail entity to the database even if no airport are assigned.
//     This is probably because hibernate generates wrong ddl queries because of poor designed entity classes
    @BeforeEach
    void setUp() {
//        Airport sourceAirport = Airport
//                .builder()
//                .name("National Airport of Molvania")
//                .city("Goong")
//                .country("Molvania")
//                .build();
//        Airport destinationAirport = Airport
//                .builder()
//                .name("Lamba")
//                .city("Gon Kong")
//                .country("Hovland")
//                .build();
//
//        airportRepository.save(sourceAirport);
//        airportRepository.save(destinationAirport);

        flightDetail = FlightDetail
                .builder()
                .departureDateTime(Timestamp.valueOf("2020-08-09 21:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2020-08-10 09:30:00"))
                .airplaneType(AirplaneType.BOEING_747)
//                .sourceAirport(sourceAirport)
//                .destinationAirport(destinationAirport)
                .build();
    }

    @Test
    @DisplayName("save flight detail successfully")
    void saveFlightDetail_shouldReturnSavedEntityWithAssignedId() {

        var savedFlightDetail = flightDetailRepository.save(flightDetail);

        assertAll(
                () -> assertThat(savedFlightDetail).isEqualTo(flightDetail),
                () -> assertThat(savedFlightDetail.getId()).isNotNull()
        );

    }
}