package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.*;
import com.dmytrobozhor.airlinereservationservice.util.compositeid.FlightCostId;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Flight Cost Repository Tests")
@DataJpaTest

// TODO: fix the embedded id problem because most of the tests fail because of it
@Disabled
class FlightCostRepositoryTests {

    @Autowired
    private FlightCostRepository flightCostRepository;

    private FlightCost flightCost;

    @BeforeAll
    static void saveDependencies(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository,
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired CalendarRepository calendarRepository
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

        SeatDetail seatDetail = SeatDetail.builder()
                .travelClass(travelClass)
                .flightDetail(flightDetail)
                .build();

        seatDetailRepository.save(seatDetail);

        Calendar validFromDate = Calendar.builder()
                .date(Date.valueOf("2020-08-09"))
                .businessDay(true)
                .build();

        calendarRepository.save(validFromDate);

        Calendar validToDate = Calendar.builder()
                .date(Date.valueOf("2020-08-10"))
                .businessDay(false)
                .build();

        calendarRepository.save(validToDate);

    }

    @BeforeEach
    void setUp(
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired CalendarRepository calendarRepository
    ) {

        var validFromDate = calendarRepository.findById(Date.valueOf("2020-08-09")).get();

        var validToDate = calendarRepository.findById(Date.valueOf("2020-08-10")).get();

        flightCost = FlightCost.builder()
                .seatDetail(seatDetailRepository.findAll().stream().findFirst().get())
                .validFromDate(validFromDate)
                .validToDate(validToDate)
                .cost(BigDecimal.valueOf(74.99))
                .build();

    }

    @Test
    @DisplayName("save flight cost")
    void whenSave_thenReturnSavedFlightCostWithId() {

        var savedFlightCost = flightCostRepository.save(flightCost);

        assertAll(
                () -> assertThat(savedFlightCost.getId()).isNotNull(),
                () -> assertThat(savedFlightCost).isEqualTo(flightCost)
        );

    }

    @Test
    @DisplayName("find flight cost by existing id")
    void whenFindById_thenReturnFlightCost() {

        var savedFlightCost = flightCostRepository.save(flightCost);

        var flightCostOptional = flightCostRepository.findById(savedFlightCost.getId());

        assertAll(
                () -> assertThat(flightCostOptional).isPresent(),
                () -> assertThat(flightCostOptional.get()).isEqualTo(savedFlightCost)
        );

    }

    @Test
    @DisplayName("find flight cost by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var flightCostOptional = flightCostRepository.findById(new FlightCostId());

        assertThat(flightCostOptional).isEmpty();

    }

    @Test
    @DisplayName("find all flight costs")
    void whenFindAll_thenReturnAllFlightCosts() {

        var flightCosts = Collections.singletonList(flightCost);

        flightCostRepository.saveAll(flightCosts);

        var savedFlightCosts = flightCostRepository.findAll();

        assertAll(
                () -> assertThat(savedFlightCosts).isNotEmpty(),
                () -> assertThat(savedFlightCosts).hasSameSizeAs(flightCosts),
                () -> assertThat(savedFlightCosts).isEqualTo(flightCosts)
        );

    }

    @Test
    @DisplayName("save all flight costs")
    void whenSaveAll_thenReturnSavedFlightCosts() {

        var flightCosts = Collections.singletonList(flightCost);

        var savedFlightCosts = flightCostRepository.saveAll(flightCosts);

        assertAll(
                () -> assertThat(savedFlightCosts).isNotEmpty(),
                () -> assertThat(savedFlightCosts).hasSameSizeAs(flightCosts),
                () -> assertThat(savedFlightCosts).isEqualTo(flightCosts)
        );

    }

    @Test
    @DisplayName("delete flight cost by id")
    void whenDeleteById_thenReturnNothing() {

        var savedFlightCost = flightCostRepository.save(flightCost);

        flightCostRepository.deleteById(savedFlightCost.getId());

        var flightCostOptional = flightCostRepository.findById(savedFlightCost.getId());

        assertThat(flightCostOptional).isEmpty();

    }

    @Test
    @DisplayName("delete flight cost")
    void whenDelete_thenReturnNothing() {

        var savedFlightCost = flightCostRepository.save(flightCost);

        flightCostRepository.delete(savedFlightCost);

        var flightCostOptional = flightCostRepository.findById(savedFlightCost.getId());

        assertThat(flightCostOptional).isEmpty();

    }

    @AfterAll
    static void clearDatabase(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository,
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired CalendarRepository calendarRepository
    ){
        calendarRepository.deleteAll();
        seatDetailRepository.deleteAll();
        flightDetailRepository.deleteAll();
        airportRepository.deleteAll();
        travelClassRepository.deleteAll();
    }

}
