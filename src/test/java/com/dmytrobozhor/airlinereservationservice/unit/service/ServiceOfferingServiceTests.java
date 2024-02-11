package com.dmytrobozhor.airlinereservationservice.unit.service;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.repository.ServiceOfferingRepository;
import com.dmytrobozhor.airlinereservationservice.service.ServiceOfferingService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ServiceOfferingMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("ServiceOffering Service Tests")
@ExtendWith(MockitoExtension.class)
class ServiceOfferingServiceTests {

    @InjectMocks
    private ServiceOfferingService serviceOfferingService;

    @Mock
    private ServiceOfferingRepository serviceOfferingRepository;

    @Mock
    private ServiceOfferingMapper serviceOfferingMapper;

    private ServiceOffering serviceOffering;

    @BeforeEach
    void setUp() {

        serviceOffering = ServiceOffering
                .builder()
                .id(ServiceOfferingId.builder().flightServiceId(1).travelClassId(1).build())
                .offered(true)
                .formDate(Timestamp.valueOf("2020-08-09 21:30:00"))
                .toDate(Timestamp.valueOf("2020-08-10 09:30:00"))
                .build();

    }

    @Test
    @DisplayName("save all service offerings")
    void whenSaveAll_thenReturnSavedServiceOfferings() {

        var serviceOfferingsForSave = Collections.singletonList(serviceOffering);

        doReturn(serviceOfferingsForSave).when(serviceOfferingRepository).saveAll(any());

        var savedServiceOfferings = serviceOfferingService.saveAll(serviceOfferingsForSave);

        assertAll(
                () -> assertThat(savedServiceOfferings).isNotEmpty(),
                () -> assertThat(savedServiceOfferings).hasSameSizeAs(serviceOfferingsForSave)
        );

        verify(serviceOfferingRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all service offerings")
    void whenFindAll_thenReturnAllServiceOfferings() {

        var serviceOfferings = Collections.singletonList(serviceOffering);

        doReturn(serviceOfferings).when(serviceOfferingRepository).findAll();

        var savedServiceOfferings = serviceOfferingService.findAll();

        assertAll(
                () -> assertThat(savedServiceOfferings).isNotEmpty(),
                () -> assertThat(savedServiceOfferings).hasSameSizeAs(serviceOfferings)
        );

        verify(serviceOfferingRepository).findAll();

    }

    @Test
    @DisplayName("save service offering")
    void whenSave_thenReturnSavedServiceOffering() {

        doReturn(serviceOffering).when(serviceOfferingRepository).save(any());

        var savedServiceOffering = serviceOfferingService.save(serviceOffering);

        assertAll(
                () -> assertThat(savedServiceOffering).isNotNull(),
                () -> assertThat(savedServiceOffering).isEqualTo(serviceOffering)
        );

        verify(serviceOfferingRepository).save(any());

    }

    @Test
    @DisplayName("delete service offering by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(serviceOfferingRepository.findById(any(ServiceOfferingId.class)))
                .thenReturn(Optional.of(serviceOffering))
                .thenReturn(Optional.empty());

        doNothing().when(serviceOfferingRepository).delete(any());

        serviceOfferingService.deleteById(serviceOffering.getId());

        assertThrows(EntityNotFoundException.class, () -> serviceOfferingService.findById(serviceOffering.getId()));

        verify(serviceOfferingRepository, times(2)).findById(any(ServiceOfferingId.class));
        verify(serviceOfferingRepository).delete(any());

    }

    @Test
    @DisplayName("delete service offering by not existing id")
    void whenDeleteById_thenThrowException() {

        when(serviceOfferingRepository.findById(any(ServiceOfferingId.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> serviceOfferingService.deleteById(serviceOffering.getId()));

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));
        verify(serviceOfferingRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find service offering by existing id")
    void whenFindById_thenReturnServiceOffering() {

        doReturn(Optional.of(serviceOffering)).when(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

        var savedServiceOffering = serviceOfferingService.findById(serviceOffering.getId());

        assertThat(savedServiceOffering).isNotNull();

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

    }

    @Test
    @DisplayName("find service offering by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

        assertThrows(EntityNotFoundException.class, () -> serviceOfferingService.findById(serviceOffering.getId()));

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

    }

    @Test
    @DisplayName("update service offering by existing id")
    void whenUpdateById_thenReturnUpdatedServiceOffering() {

        var persistedServiceOffering = ServiceOffering
                .builder()
                .id(ServiceOfferingId.builder().flightServiceId(1).travelClassId(2).build())
                .offered(true)
                .formDate(Timestamp.valueOf("2024-07-09 21:30:00"))
                .toDate(Timestamp.valueOf("2024-08-15 09:30:00"))
                .build();

        doReturn(Optional.of(persistedServiceOffering))
                .when(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

        persistedServiceOffering.setOffered(serviceOffering.getOffered());
        persistedServiceOffering.setFormDate(serviceOffering.getFormDate());
        persistedServiceOffering.setToDate(serviceOffering.getToDate());

        doNothing().when(serviceOfferingMapper).updateServiceOfferingPartial(any(), any());

        doReturn(persistedServiceOffering).when(serviceOfferingRepository).save(any());

        var updatedServiceOffering = serviceOfferingService
                .updateById(persistedServiceOffering.getId(), serviceOffering);

        assertAll(
                () -> assertThat(updatedServiceOffering.getOffered()).isEqualTo(serviceOffering.getOffered()),
                () -> assertThat(updatedServiceOffering.getFormDate()).isEqualTo(serviceOffering.getFormDate()),
                () -> assertThat(updatedServiceOffering.getToDate()).isEqualTo(serviceOffering.getToDate())
        );

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));
        verify(serviceOfferingMapper).updateServiceOfferingPartial(any(), any());
        verify(serviceOfferingRepository).save(any());

    }

    @Test
    @DisplayName("update service offering by not existing id")
    void whenUpdateById_thenThrowException() {

        var id = ServiceOfferingId
                .builder()
                .flightServiceId(1)
                .travelClassId(2)
                .build();

        doReturn(Optional.empty()).when(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

        assertThrows(EntityNotFoundException.class, () -> serviceOfferingService.updateById(id, serviceOffering));

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

    }

    @Test
    @DisplayName("update or create service offering by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedServiceOffering() {

        var persistedServiceOffering = ServiceOffering
                .builder()
                .id(ServiceOfferingId.builder().flightServiceId(1).travelClassId(2).build())
                .offered(true)
                .formDate(Timestamp.valueOf("2024-07-09 21:30:00"))
                .toDate(Timestamp.valueOf("2024-08-15 09:30:00"))
                .build();

        doReturn(Optional.of(persistedServiceOffering))
                .when(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

        persistedServiceOffering.setOffered(serviceOffering.getOffered());
        persistedServiceOffering.setFormDate(serviceOffering.getFormDate());
        persistedServiceOffering.setToDate(serviceOffering.getToDate());

        doNothing().when(serviceOfferingMapper).updateServiceOfferingPartial(any(), any());

        doReturn(persistedServiceOffering).when(serviceOfferingRepository).save(any());

        var updatedServiceOffering = serviceOfferingService
                .updateOrCreateById(persistedServiceOffering.getId(), serviceOffering);

        assertAll(
                () -> assertThat(updatedServiceOffering.getOffered()).isEqualTo(serviceOffering.getOffered()),
                () -> assertThat(updatedServiceOffering.getFormDate()).isEqualTo(serviceOffering.getFormDate()),
                () -> assertThat(updatedServiceOffering.getToDate()).isEqualTo(serviceOffering.getToDate())
        );

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));
        verify(serviceOfferingMapper).updateServiceOfferingPartial(any(), any());
        verify(serviceOfferingRepository).save(any());

    }

    @Test
    @DisplayName("update or create service offering by not existing id")
    void wheUpdateOrCreateById_thenCreateNewServiceOffering() {

        var id = ServiceOfferingId
                .builder()
                .flightServiceId(1)
                .travelClassId(2)
                .build();

        doReturn(Optional.empty()).when(serviceOfferingRepository).findById(any(ServiceOfferingId.class));

        doReturn(serviceOffering).when(serviceOfferingRepository).save(any());

        var createdServiceOffering = serviceOfferingService.updateOrCreateById(id, serviceOffering);

        assertAll(
                () -> assertThat(createdServiceOffering).isNotNull(),
                () -> assertThat(createdServiceOffering).isEqualTo(serviceOffering)
        );

        verify(serviceOfferingRepository).findById(any(ServiceOfferingId.class));
        verify(serviceOfferingRepository).save(any());

    }
}
