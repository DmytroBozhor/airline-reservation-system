package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Travel Class Repository Tests")
@DataJpaTest
class TravelClassRepositoryTests {

    @Autowired
    private TravelClassRepository travelClassRepository;

    private TravelClass travelClass;

    @BeforeEach
    void setUp() {

        travelClass = TravelClass
                .builder()
                .name(TravelClassName.BUSINESS_CLASS)
                .capacity(20)
                .build();

    }

    @Test
    @DisplayName("save travel class")
    void whenSave_thenReturnSavedTravelClassWithId() {

        var savedTravelClass = travelClassRepository.save(travelClass);

        assertAll(
                () -> assertThat(savedTravelClass.getId()).isNotNull(),
                () -> assertThat(savedTravelClass).isEqualTo(travelClass)
        );

    }

    @Test
    @DisplayName("find travel class by existing id")
    void whenFindById_thenReturnTravelClass() {

        var savedTravelClass = travelClassRepository.save(travelClass);

        var travelClassOptional = travelClassRepository.findById(savedTravelClass.getId());

        assertAll(
                () -> assertThat(travelClassOptional).isPresent(),
                () -> assertThat(travelClassOptional.get()).isEqualTo(savedTravelClass)
        );

    }

    @Test
    @DisplayName("find travel class by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var travelClassOptional = travelClassRepository.findById(1);

        assertThat(travelClassOptional).isEmpty();

    }

    @Test
    @DisplayName("find all travel classes")
    void whenFindAll_thenReturnAllTravelClasses() {

        var travelClasses = Collections.singletonList(travelClass);

        travelClassRepository.saveAll(travelClasses);

        var savedTravelClasses = travelClassRepository.findAll();

        assertAll(
                () -> assertThat(savedTravelClasses).isNotEmpty(),
                () -> assertThat(savedTravelClasses).hasSameSizeAs(travelClasses),
                () -> assertThat(savedTravelClasses).isEqualTo(travelClasses)
        );

    }

    @Test
    @DisplayName("save all travel classes")
    void whenSaveAll_thenReturnSavedTravelClasses() {

        var travelClasses = Collections.singletonList(travelClass);

        var savedTravelClasses = travelClassRepository.saveAll(travelClasses);

        assertAll(
                () -> assertThat(savedTravelClasses).isNotEmpty(),
                () -> assertThat(savedTravelClasses).hasSameSizeAs(travelClasses),
                () -> assertThat(savedTravelClasses).isEqualTo(travelClasses)
        );

    }

    @Test
    @DisplayName("delete travel class by id")
    void whenDeleteById_thenReturnNothing() {

        var savedTravelClass = travelClassRepository.save(travelClass);

        travelClassRepository.deleteById(savedTravelClass.getId());

        var travelClassOptional = travelClassRepository.findById(savedTravelClass.getId());

        assertThat(travelClassOptional).isEmpty();

    }

    @Test
    @DisplayName("delete travel class")
    void whenDelete_thenReturnNothing() {

        var savedTravelClass = travelClassRepository.save(travelClass);

        travelClassRepository.delete(savedTravelClass);

        var travelClassOptional = travelClassRepository.findById(savedTravelClass.getId());

        assertThat(travelClassOptional).isEmpty();

    }

}