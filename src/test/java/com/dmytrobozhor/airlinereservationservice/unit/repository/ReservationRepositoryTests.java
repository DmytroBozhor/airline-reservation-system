package com.dmytrobozhor.airlinereservationservice.unit.repository;

import com.dmytrobozhor.airlinereservationservice.domain.*;
import com.dmytrobozhor.airlinereservationservice.repository.*;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Reservation Repository Tests")
@DataJpaTest
class ReservationRepositoryTests {

    @Autowired
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    @BeforeAll
    static void saveDependencies(
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository,
            @Autowired PassengerRepository passengerRepository
    ) {

        TravelClass travelClass = TravelClass
                .builder()
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

        SeatDetail seatDetail = SeatDetail
                .builder()
                .travelClass(travelClass)
                .flightDetail(flightDetail)
                .build();

        seatDetailRepository.save(seatDetail);

        Passenger passenger = Passenger
                .builder()
                .firstName("Bile")
                .lastName("Harrington")
                .phoneNumber("7452186394")
                .build();

        passengerRepository.save(passenger);

    }

    @BeforeEach
    void setUpReservation(
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired PassengerRepository passengerRepository
    ) {

        reservation = Reservation.builder()
                .passenger(passengerRepository.findAll().stream().findFirst().get())
                .seatDetail(seatDetailRepository.findAll().stream().findFirst().get())
                .reservationDateTime(Timestamp.from(Instant.now()))
                .build();

    }

    @Test
    @DisplayName("save reservation")
    void whenSave_thenReturnSavedReservationWithId() {

        var savedReservation = reservationRepository.save(reservation);

        assertAll(
                () -> assertThat(savedReservation.getId()).isNotNull(),
                () -> assertThat(savedReservation).isEqualTo(reservation)
        );

    }

    @Test
    @DisplayName("find reservation by existing id")
    void whenFindById_thenReturnReservation() {

        var savedReservation = reservationRepository.save(reservation);

        var reservationOptional = reservationRepository.findById(savedReservation.getId());

        assertAll(
                () -> assertThat(reservationOptional).isPresent(),
                () -> assertThat(reservationOptional.get()).isEqualTo(savedReservation)
        );

    }

    @Test
    @DisplayName("find reservation by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var reservationOptional = reservationRepository.findById(1);

        assertThat(reservationOptional).isEmpty();

    }

    @Test
    @DisplayName("find all reservations")
    void whenFindAll_thenReturnAllReservations() {

        var reservations = Collections.singletonList(reservation);

        reservationRepository.saveAll(reservations);

        var savedReservations = reservationRepository.findAll();

        assertAll(
                () -> assertThat(savedReservations).isNotEmpty(),
                () -> assertThat(savedReservations).hasSameSizeAs(reservations),
                () -> assertThat(savedReservations).isEqualTo(reservations)
        );

    }

    @Test
    @DisplayName("save all reservations")
    void whenSaveAll_thenReturnSavedReservations() {

        var reservations = Collections.singletonList(reservation);

        var savedReservations = reservationRepository.saveAll(reservations);

        assertAll(
                () -> assertThat(savedReservations).isNotEmpty(),
                () -> assertThat(savedReservations).hasSameSizeAs(reservations),
                () -> assertThat(savedReservations).isEqualTo(reservations)
        );

    }

    @Test
    @DisplayName("delete reservation by id")
    void whenDeleteById_thenReturnNothing() {

        var savedReservation = reservationRepository.save(reservation);

        reservationRepository.deleteById(savedReservation.getId());

        var reservationOptional = reservationRepository.findById(savedReservation.getId());

        assertThat(reservationOptional).isEmpty();

    }

    @Test
    @DisplayName("delete reservation")
    void whenDelete_thenReturnNothing() {

        var savedReservation = reservationRepository.save(reservation);

        reservationRepository.delete(savedReservation);

        var reservationOptional = reservationRepository.findById(savedReservation.getId());

        assertThat(reservationOptional).isEmpty();

    }

    @AfterAll
    static void clearDatabase(
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository,
            @Autowired PassengerRepository passengerRepository
    ) {
        seatDetailRepository.deleteAll();
        flightDetailRepository.deleteAll();
        travelClassRepository.deleteAll();
        airportRepository.deleteAll();
        passengerRepository.deleteAll();
    }
}
