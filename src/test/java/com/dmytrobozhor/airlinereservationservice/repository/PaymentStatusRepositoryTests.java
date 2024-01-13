package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.*;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.enums.Status;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Payment Status Repository Tests")
@DataJpaTest
class PaymentStatusRepositoryTests {

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    private PaymentStatus paymentStatus;

    @BeforeAll
    static void saveDependencies(
            @Autowired ReservationRepository reservationRepository,
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository,
            @Autowired PassengerRepository passengerRepository
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

        Passenger passenger = Passenger.builder()
                .firstName("Bile")
                .lastName("Harrington")
                .phoneNumber("7452186394")
                .build();

        passengerRepository.save(passenger);

        Reservation reservation = Reservation.builder()
                .passenger(passenger)
                .seatDetail(seatDetail)
                .reservationDateTime(Timestamp.from(Instant.now()))
                .build();

        reservationRepository.save(reservation);

    }

    @BeforeEach
    void setUp(@Autowired ReservationRepository reservationRepository) {

        paymentStatus = PaymentStatus.builder()
                .status(Status.Y)
                .dueDate(Date.valueOf(LocalDate.now().plusDays(10)))
                .amount(BigDecimal.valueOf(49.99D))
                .reservation(reservationRepository.findAll().stream().findFirst().get())
                .build();

    }

    @Test
    @DisplayName("save payment status")
    void whenSave_thenReturnSavedPaymentStatusWithId() {

        var savedPaymentStatus = paymentStatusRepository.save(paymentStatus);

        assertAll(
                () -> assertThat(savedPaymentStatus.getId()).isNotNull(),
                () -> assertThat(savedPaymentStatus).isEqualTo(paymentStatus)
        );

    }

    @Test
    @DisplayName("find payment status by existing id")
    void whenFindById_thenReturnPaymentStatus() {

        var savedPaymentStatus = paymentStatusRepository.save(paymentStatus);

        var paymentStatusOptional = paymentStatusRepository.findById(savedPaymentStatus.getId());

        assertAll(
                () -> assertThat(paymentStatusOptional).isPresent(),
                () -> assertThat(paymentStatusOptional.get()).isEqualTo(savedPaymentStatus)
        );

    }

    @Test
    @DisplayName("find payment status by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var paymentStatusOptional = paymentStatusRepository.findById(1);

        assertThat(paymentStatusOptional).isEmpty();

    }

    @Test
    @DisplayName("find all payment statuses")
    void whenFindAll_thenReturnAllPaymentStatuses() {

        var paymentStatuses = Collections.singletonList(paymentStatus);

        paymentStatusRepository.saveAll(paymentStatuses);

        var savedPaymentStatuses = paymentStatusRepository.findAll();

        assertAll(
                () -> assertThat(savedPaymentStatuses).isNotEmpty(),
                () -> assertThat(savedPaymentStatuses).hasSameSizeAs(paymentStatuses),
                () -> assertThat(savedPaymentStatuses).isEqualTo(paymentStatuses)
        );

    }

    @Test
    @DisplayName("save all payment statuses")
    void whenSaveAll_thenReturnSavedPaymentStatuses() {

        var paymentStatuses = Collections.singletonList(paymentStatus);

        var savedPaymentStatuses = paymentStatusRepository.saveAll(paymentStatuses);

        assertAll(
                () -> assertThat(savedPaymentStatuses).isNotEmpty(),
                () -> assertThat(savedPaymentStatuses).hasSameSizeAs(paymentStatuses),
                () -> assertThat(savedPaymentStatuses).isEqualTo(paymentStatuses)
        );

    }

    @Test
    @DisplayName("delete payment status by id")
    void whenDeleteById_thenReturnNothing() {

        var savedPaymentStatus = paymentStatusRepository.save(paymentStatus);

        paymentStatusRepository.deleteById(savedPaymentStatus.getId());

        var paymentStatusOptional = paymentStatusRepository.findById(savedPaymentStatus.getId());

        assertThat(paymentStatusOptional).isEmpty();

    }

    @Test
    @DisplayName("delete payment status")
    void whenDelete_thenReturnNothing() {

        var savedPaymentStatus = paymentStatusRepository.save(paymentStatus);

        paymentStatusRepository.delete(savedPaymentStatus);

        var paymentStatusOptional = paymentStatusRepository.findById(savedPaymentStatus.getId());

        assertThat(paymentStatusOptional).isEmpty();

    }

    @AfterAll
    static void clearDatabase(
            @Autowired ReservationRepository reservationRepository,
            @Autowired SeatDetailRepository seatDetailRepository,
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired AirportRepository airportRepository,
            @Autowired FlightDetailRepository flightDetailRepository,
            @Autowired PassengerRepository passengerRepository
    ) {
        reservationRepository.deleteAll();
        passengerRepository.deleteAll();
        seatDetailRepository.deleteAll();
        flightDetailRepository.deleteAll();
        airportRepository.deleteAll();
        travelClassRepository.deleteAll();
    }
}
