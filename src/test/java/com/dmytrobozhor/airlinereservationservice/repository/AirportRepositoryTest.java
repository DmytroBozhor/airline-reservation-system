package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Airport Repository Test")
@TestMethodOrder(MethodOrderer.DisplayName.class)
@DataJpaTest
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
//@Rollback(value = true)
//@SpringBootTest
class AirportRepositoryTest {

//    TODO: find out why DI with constructor does not work. Only field injection works

    @Autowired
    private AirportRepository airportRepository;

//    @PersistenceContext
//    private TestEntityManager entityManager;

    private Airport airport;

    @BeforeEach
    void setUp() {
        airport = Airport
                .builder()
                .name("Northern Airport")
                .city("Billingua")
                .country("China")
                .build();
    }

    @Test
    @DisplayName("save airport and return the saved entity")
    void saveAirport_shouldReturnSavedEntity() {

        var savedAirport = airportRepository.save(airport);

        assertAll(
                () -> assertThat(savedAirport).isNotNull(),
                () -> assertThat(savedAirport).isEqualTo(airport),
                () -> assertThat(savedAirport.getId()).isNotNull()
        );

    }

    @Test
    @DisplayName("find airport by existing id")
    void findAirportById_shouldReturnFoundEntity() {

        var savedAirport = airportRepository.save(airport);

        var airportOptional = airportRepository.findById(savedAirport.getId());

        assertAll(
                () -> assertThat(airportOptional).isNotEmpty(),
                () -> assertThat(airportOptional).contains(savedAirport)
        );

    }

    @Test
    @DisplayName("find airport by not existing id")
    void findAirportById_shouldReturnNothing() {

        var airportOptional = airportRepository.findById(1);

        assertThat(airportOptional).isEmpty();

    }

    @Test
    @DisplayName("delete airport by id")
    void deleteAirportById_shouldSuccessfullyDeleteAirport() {

        var savedAirport = airportRepository.save(airport);

        airportRepository.deleteById(savedAirport.getId());

        var deletedAirportOptional = airportRepository.findById(savedAirport.getId());

        assertThat(deletedAirportOptional).isNotPresent();

    }

    @Test
    @DisplayName("delete airport")
    void deleteAirport_shouldSuccessfullyDeleteAirport() {

        var savedAirport = airportRepository.save(airport);

        airportRepository.delete(savedAirport);

        var deletedAirportOptional = airportRepository.findById(savedAirport.getId());

        assertThat(deletedAirportOptional).isNotPresent();

    }

    @Test
    @DisplayName("save all airports")
    void saveAllAirports_shouldReturnSavedEntities() {

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
    void findAllAirports_shouldReturnAllEntities() {

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