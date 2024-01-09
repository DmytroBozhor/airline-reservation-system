package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@Tag("unit-fast")
@DisplayName("Airport Repository Test")
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_METHOD) //TODO: will delete it later because it is default
@TestMethodOrder(MethodOrderer.DisplayName.class) //TODO: will delete it too because it is anti-pattern
//@ExtendWith(value = {SpringExtension.class})
class AirportRepositoryTest {

    private final AirportRepository airportRepository;

    @Test
    @DisplayName("save airport")
    void saveAirport() {
        Airport airport = Airport
                .builder()
                .name("Northern Airport")
                .city("Billingua")
                .country("China")
                .build();

        Airport savedAirport = airportRepository.save(airport);

//        assertThat(savedAirport).isEqualTo(airport);

    }
}