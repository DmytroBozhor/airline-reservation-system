package com.dmytrobozhor.airlinereservationservice.unit.repository;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.FlightServiceRepository;
import com.dmytrobozhor.airlinereservationservice.repository.ServiceOfferingRepository;
import com.dmytrobozhor.airlinereservationservice.repository.TravelClassRepository;
import com.dmytrobozhor.airlinereservationservice.util.compositeid.ServiceOfferingId;
import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("repository-tests")
@Tag("unit-fast")
@DisplayName("Service Offering Repository Tests")
@DataJpaTest

// TODO: fix the embedded id problem. almost all the tests fail because of it
@Disabled
class ServiceOfferingRepositoryTests {

    @Autowired
    private ServiceOfferingRepository serviceOfferingRepository;

    private ServiceOffering serviceOffering;

    @BeforeAll
    static void saveDependencies(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired FlightServiceRepository flightServiceRepository
    ) {

        TravelClass travelClass = TravelClass
                .builder()
                .name(TravelClassName.BUSINESS_CLASS)
                .capacity(20)
                .build();

        travelClassRepository.save(travelClass);

        FlightService flightService = FlightService
                .builder()
                .serviceName(ServiceName.WIFI)
                .build();

        flightServiceRepository.save(flightService);

    }

    @BeforeEach
    void setUp(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired FlightServiceRepository flightServiceRepository
    ) {

        serviceOffering = ServiceOffering
                .builder()
                .offered(true)
                .travelClass(travelClassRepository.findAll().stream().findFirst().get())
                .flightService(flightServiceRepository.findAll().stream().findFirst().get())
                .build();

    }

    @Test
    @DisplayName("save service offering")
    void whenSave_thenReturnSavedServiceOfferingWithId() {

        var savedServiceOffering = serviceOfferingRepository.save(serviceOffering);

        assertAll(
                () -> assertThat(savedServiceOffering.getId()).isNotNull(),
                () -> assertThat(savedServiceOffering).isEqualTo(serviceOffering)
        );

    }

    @Test
    @DisplayName("find service offering by existing id")
    void whenFindById_thenReturnServiceOffering() {

        var savedServiceOffering = serviceOfferingRepository.save(serviceOffering);

        var serviceOfferingOptional = serviceOfferingRepository.findById(savedServiceOffering.getId());

        assertAll(
                () -> assertThat(serviceOfferingOptional).isPresent(),
                () -> assertThat(serviceOfferingOptional.get()).isEqualTo(savedServiceOffering)
        );

    }

    @Test
    @DisplayName("find service offering by not existing id")
    void whenFindById_thenReturnOptionalEmpty() {

        var serviceOfferingOptional = serviceOfferingRepository.findById(new ServiceOfferingId());

        assertThat(serviceOfferingOptional).isEmpty();

    }

    @Test
    @DisplayName("find all service offerings")
    void whenFindAll_thenReturnAllServiceOfferings() {

        var serviceOfferings = Collections.singletonList(serviceOffering);

        serviceOfferingRepository.saveAll(serviceOfferings);

        var savedServiceOfferings = serviceOfferingRepository.findAll();

        assertAll(
                () -> assertThat(savedServiceOfferings).isNotEmpty(),
                () -> assertThat(savedServiceOfferings).hasSameSizeAs(serviceOfferings),
                () -> assertThat(savedServiceOfferings).isEqualTo(serviceOfferings)
        );

    }

    @Test
    @DisplayName("save all service offerings")
    void whenSaveAll_thenReturnSavedServiceOfferings() {

        var serviceOfferings = Collections.singletonList(serviceOffering);

        var savedServiceOfferings = serviceOfferingRepository.saveAll(serviceOfferings);

        assertAll(
                () -> assertThat(savedServiceOfferings).isNotEmpty(),
                () -> assertThat(savedServiceOfferings).hasSameSizeAs(serviceOfferings),
                () -> assertThat(savedServiceOfferings).isEqualTo(serviceOfferings)
        );

    }

    @Test
    @DisplayName("delete service offering by id")
    void whenDeleteById_thenReturnNothing() {

        var savedServiceOffering = serviceOfferingRepository.save(serviceOffering);

        serviceOfferingRepository.deleteById(savedServiceOffering.getId());

        var serviceOfferingOptional = serviceOfferingRepository.findById(savedServiceOffering.getId());

        assertThat(serviceOfferingOptional).isEmpty();

    }

    @Test
    @DisplayName("delete service offering")
    void whenDelete_thenReturnNothing() {

        var savedServiceOffering = serviceOfferingRepository.save(serviceOffering);

        serviceOfferingRepository.delete(savedServiceOffering);

        var serviceOfferingOptional = serviceOfferingRepository.findById(savedServiceOffering.getId());

        assertThat(serviceOfferingOptional).isEmpty();

    }

    @AfterAll
    static void clearDatabase(
            @Autowired TravelClassRepository travelClassRepository,
            @Autowired FlightServiceRepository flightServiceRepository
    ) {
        travelClassRepository.deleteAll();
        flightServiceRepository.deleteAll();
    }
}
