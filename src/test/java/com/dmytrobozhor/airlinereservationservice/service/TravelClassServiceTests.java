package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.TravelClassRepository;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import com.dmytrobozhor.airlinereservationservice.util.mappers.TravelClassMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("TravelClass Service Tests")
@ExtendWith(MockitoExtension.class)
class TravelClassServiceTests {

    @InjectMocks
    private TravelClassService travelClassService;

    @Mock
    private TravelClassRepository travelClassRepository;

    @Mock
    private TravelClassMapper travelClassMapper;

    private TravelClass travelClass;

    @BeforeEach
    void setUp() {

        travelClass = TravelClass
                .builder()
                .id(1)
                .name(TravelClassName.BUSINESS_CLASS)
                .capacity(20)
                .build();

    }

    @Test
    @DisplayName("save all travel classes")
    void whenSaveAll_thenReturnSavedTravelClasses() {

        var travelClassesForSave = Collections.singletonList(travelClass);

        doReturn(travelClassesForSave).when(travelClassRepository).saveAll(any());

        var savedTravelClasses = travelClassService.saveAll(travelClassesForSave);

        assertAll(
                () -> assertThat(savedTravelClasses).isNotEmpty(),
                () -> assertThat(savedTravelClasses).hasSameSizeAs(travelClassesForSave)
        );

        verify(travelClassRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all travel classes")
    void whenFindAll_thenReturnAllTravelClasses() {

        var travelClasses = Collections.singletonList(travelClass);

        doReturn(travelClasses).when(travelClassRepository).findAll();

        var savedTravelClasses = travelClassService.findAll();

        assertAll(
                () -> assertThat(savedTravelClasses).isNotEmpty(),
                () -> assertThat(savedTravelClasses).hasSameSizeAs(travelClasses)
        );

        verify(travelClassRepository).findAll();

    }

    @Test
    @DisplayName("save travel class")
    void whenSave_thenReturnSavedTravelClass() {

        doReturn(travelClass).when(travelClassRepository).save(any());

        var savedTravelClass = travelClassService.save(travelClass);

        assertAll(
                () -> assertThat(savedTravelClass).isNotNull(),
                () -> assertThat(savedTravelClass).isEqualTo(travelClass)
        );

        verify(travelClassRepository).save(any());

    }

    @Test
    @DisplayName("delete travel class by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(travelClassRepository.findById(anyInt()))
                .thenReturn(Optional.of(travelClass))
                .thenReturn(Optional.empty());

        doNothing().when(travelClassRepository).delete(any());

        travelClassService.deleteById(travelClass.getId());

        assertThrows(EntityNotFoundException.class, () -> travelClassService.findById(travelClass.getId()));

        verify(travelClassRepository, times(2)).findById(anyInt());
        verify(travelClassRepository).delete(any());

    }

    @Test
    @DisplayName("delete travel class by not existing id")
    void whenDeleteById_thenThrowException() {

        when(travelClassRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> travelClassService.deleteById(travelClass.getId()));

        verify(travelClassRepository).findById(anyInt());
        verify(travelClassRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find travel class by existing id")
    void whenFindById_thenReturnTravelClass() {

        doReturn(Optional.of(travelClass)).when(travelClassRepository).findById(anyInt());

        var savedTravelClass = travelClassService.findById(travelClass.getId());

        assertThat(savedTravelClass).isNotNull();

        verify(travelClassRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find travel class by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(travelClassRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> travelClassService.findById(travelClass.getId()));

        verify(travelClassRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update travel class by existing id")
    void whenUpdateById_thenReturnUpdatedTravelClass() {

        var persistedTravelClass = TravelClass.builder()
                .id(2)
                .name(TravelClassName.FIRST_CLASS)
                .capacity(15)
                .build();

        doReturn(Optional.of(persistedTravelClass)).when(travelClassRepository).findById(anyInt());

        persistedTravelClass.setName(travelClass.getName());
        persistedTravelClass.setCapacity(travelClass.getCapacity());

        doNothing().when(travelClassMapper).updateTravelClassPartial(any(), any());

        doReturn(persistedTravelClass).when(travelClassRepository).save(any());

        var updatedTravelClass = travelClassService.updateById(persistedTravelClass.getId(), travelClass);

        assertAll(
                () -> assertThat(updatedTravelClass.getName()).isEqualTo(travelClass.getName()),
                () -> assertThat(updatedTravelClass.getCapacity()).isEqualTo(travelClass.getCapacity())
        );

        verify(travelClassRepository).findById(anyInt());
        verify(travelClassMapper).updateTravelClassPartial(any(), any());
        verify(travelClassRepository).save(any());

    }

    @Test
    @DisplayName("update travel class by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(travelClassRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> travelClassService.updateById(2, travelClass));

        verify(travelClassRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create travel class by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedTravelClass() {

        var persistedTravelClass = TravelClass.builder()
                .id(2)
                .name(TravelClassName.FIRST_CLASS)
                .capacity(15)
                .build();

        doReturn(Optional.of(persistedTravelClass)).when(travelClassRepository).findById(anyInt());

        persistedTravelClass.setName(travelClass.getName());
        persistedTravelClass.setCapacity(travelClass.getCapacity());

        doNothing().when(travelClassMapper).updateTravelClassPartial(any(), any());

        doReturn(persistedTravelClass).when(travelClassRepository).save(any());

        var updatedTravelClass = travelClassService.updateOrCreateById(persistedTravelClass.getId(), travelClass);

        assertAll(
                () -> assertThat(updatedTravelClass.getName()).isEqualTo(travelClass.getName()),
                () -> assertThat(updatedTravelClass.getCapacity()).isEqualTo(travelClass.getCapacity())
        );

        verify(travelClassRepository).findById(anyInt());
        verify(travelClassMapper).updateTravelClassPartial(any(), any());
        verify(travelClassRepository).save(any());

    }

    @Test
    @DisplayName("update or create travel class by not existing id")
    void wheUpdateOrCreateById_thenCreateNewTravelClass() {

        doReturn(Optional.empty()).when(travelClassRepository).findById(anyInt());

        doReturn(travelClass).when(travelClassRepository).save(any());

        var createdTravelClass = travelClassService.updateOrCreateById(2, travelClass);

        assertAll(
                () -> assertThat(createdTravelClass).isNotNull(),
                () -> assertThat(createdTravelClass).isEqualTo(travelClass)
        );

        verify(travelClassRepository).findById(anyInt());
        verify(travelClassRepository).save(any());

    }
}
