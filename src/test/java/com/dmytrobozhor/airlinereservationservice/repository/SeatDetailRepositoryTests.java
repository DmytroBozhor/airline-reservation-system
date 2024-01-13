package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Seat Detail Repository Tests")
@DataJpaTest
class SeatDetailRepositoryTests {

    @Autowired
    private SeatDetailRepository seatDetailRepository;

    private SeatDetail seatDetail;

    @BeforeAll
    static void saveDependencies(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository
    ) {

        TravelClass travelClass = TravelClass.builder()
                .name(TravelClassName.BUSINESS_CLASS)
                .capacity(20)
                .build();

        travelClassRepository.save(travelClass);

        Airport sourceAirport = Airport
                .builder()
                .name("National Airport of Molvania")
                .city("Goong")
                .country("Molvania")
                .build();

        airportRepository.save(sourceAirport);

        Airport destinationAirport = Airport
                .builder()
                .name("Lamba")
                .city("Gon Kong")
                .country("Hovland")
                .build();

        airportRepository.save(destinationAirport);

        FlightDetail flightDetail = FlightDetail
                .builder()
                .departureDateTime(Timestamp.valueOf("2020-08-09 21:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2020-08-10 09:30:00"))
                .airplaneType(AirplaneType.BOEING_747)
                .sourceAirport(sourceAirport)
                .destinationAirport(destinationAirport)
                .build();

        flightDetailRepository.save(flightDetail);

    }

    @BeforeEach
    void setUp(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired FlightDetailRepository flightDetailRepository
    ) {

        seatDetail = SeatDetail.builder()
                .travelClass(travelClassRepository.findAll().stream().findFirst().get())
                .flightDetail(flightDetailRepository.findAll().stream().findFirst().get())
                .build();

    }

    @Test
    @DisplayName("save seat detail")
    void whenSave_thenReturnSavedSeatDetailWithId() {

        var savedSeatDetail = seatDetailRepository.save(seatDetail);

        assertAll(
                () -> assertThat(savedSeatDetail.getId()).isNotNull(),
                () -> assertThat(savedSeatDetail).isEqualTo(seatDetail)
        );

    }

    @Test
    @DisplayName("find seat detail by existing id")
    void whenFindById_thenReturnSeatDetail() {

        var savedSeatDetail = seatDetailRepository.save(seatDetail);

        var seatDetailOptional = seatDetailRepository.findById(savedSeatDetail.getId());

        assertAll(
                () -> assertThat(seatDetailOptional).isPresent(),
                () -> assertThat(seatDetailOptional.get()).isEqualTo(savedSeatDetail)
        );

    }

    @Test
    @DisplayName("find seat detail by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var seatDetailOptional = seatDetailRepository.findById(1);

        assertThat(seatDetailOptional).isEmpty();

    }

    @Test
    @DisplayName("find all seat details")
    void whenFindAll_thenReturnAllSeatDetails() {

        var seatDetails = Collections.singletonList(seatDetail);

        seatDetailRepository.saveAll(seatDetails);

        var savedSeatDetails = seatDetailRepository.findAll();

        assertAll(
                () -> assertThat(savedSeatDetails).isNotEmpty(),
                () -> assertThat(savedSeatDetails).hasSameSizeAs(seatDetails),
                () -> assertThat(savedSeatDetails).isEqualTo(seatDetails)
        );

    }

    @Test
    @DisplayName("save all seat details")
    void whenSaveAll_thenReturnSavedSeatDetails() {

        var seatDetails = Collections.singletonList(seatDetail);

        var savedSeatDetails = seatDetailRepository.saveAll(seatDetails);

        assertAll(
                () -> assertThat(savedSeatDetails).isNotEmpty(),
                () -> assertThat(savedSeatDetails).hasSameSizeAs(seatDetails),
                () -> assertThat(savedSeatDetails).isEqualTo(seatDetails)
        );

    }

    @Test
    @DisplayName("delete seat detail by id")
    void whenDeleteById_thenReturnNothing() {

        var savedSeatDetail = seatDetailRepository.save(seatDetail);

        seatDetailRepository.deleteById(savedSeatDetail.getId());

        var seatDetailOptional = seatDetailRepository.findById(savedSeatDetail.getId());

        assertThat(seatDetailOptional).isEmpty();

    }

    @Test
    @DisplayName("delete seat detail")
    void whenDelete_thenReturnNothing() {

        var savedSeatDetail = seatDetailRepository.save(seatDetail);

        seatDetailRepository.delete(savedSeatDetail);

        var seatDetailOptional = seatDetailRepository.findById(savedSeatDetail.getId());

        assertThat(seatDetailOptional).isEmpty();

    }

    @AfterAll
    static void clearDatabase(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository
    ) {
        flightDetailRepository.deleteAll();
        travelClassRepository.deleteAll();
        airportRepository.deleteAll();
    }
}
