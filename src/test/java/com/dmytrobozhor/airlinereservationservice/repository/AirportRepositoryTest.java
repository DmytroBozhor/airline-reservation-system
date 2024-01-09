package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("unit-fast")
@DisplayName("Airport Repository Test")
//TODO: will delete it later because it is default
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
//TODO: will delete it too because it is anti-pattern
@TestMethodOrder(MethodOrderer.DisplayName.class)
//TODO: find out more about this annotation
@DataJpaTest
//@ExtendWith(value = {SpringExtension.class})
//@SpringBootTest
class AirportRepositoryTest {

//    TODO: find out why DI with constructor does not work. Only field injection works

    @Autowired
    private AirportRepository airportRepository;

    private Airport airport;

//    TODO: find out why we do not need to remove airports from the db after each test

    @BeforeEach
    void setup() {
        airport = Airport
                .builder()
                .name("Northern Airport")
                .city("Billingua")
                .country("China")
                .build();
    }

    @Test
    @DisplayName("save airport")
    void saveAirport() {

        var savedAirport = airportRepository.save(airport);

        assertAll(
                () -> assertThat(savedAirport).isNotNull(),
                () -> assertThat(savedAirport).isEqualTo(airport),
                () -> assertThat(savedAirport.getId()).isNotNull()
        );

    }

    @Test
    @DisplayName("find airport by id")
    void findAirportById() {

        var savedAirport = airportRepository.save(airport);

        var airportOptional = airportRepository.findById(savedAirport.getId());

        assertAll(
                () -> assertThat(airportOptional).isNotEmpty(),
                () -> assertThat(airportOptional.get()).isEqualTo(savedAirport)
        );

    }

    @Test
    @DisplayName("save all airports")
    void saveAllAirports() {

        var airportsForSave = Collections.singletonList(airport);

        var savedAirports = airportRepository.saveAll(airportsForSave);

        assertAll(
                () -> assertThat(savedAirports).isNotEmpty(),
                () -> assertThat(savedAirports).hasSameSizeAs(airportsForSave),
                () -> assertThat(savedAirports).isEqualTo(airportsForSave)
        );

    }

    @Test
    @DisplayName("find all airports")
    void findAllAirports() {

        var airportsForSave = Collections.singletonList(airport);
        airportRepository.saveAll(airportsForSave);

        var savedAirports = airportRepository.findAll();

        assertAll(
                () -> assertThat(savedAirports).isNotEmpty(),
                () -> assertThat(savedAirports).hasSameSizeAs(airportsForSave),
                () -> assertThat(savedAirports).isEqualTo(airportsForSave)
        );

    }

}