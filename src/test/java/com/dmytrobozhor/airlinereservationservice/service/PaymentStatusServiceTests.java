package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.PaymentStatus;
import com.dmytrobozhor.airlinereservationservice.repository.PaymentStatusRepository;
import com.dmytrobozhor.airlinereservationservice.util.enums.Status;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PaymentStatusMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Tag("service-tests")
@Tag("unit-fast")
@DisplayName("PaymentStatus Service Tests")
@ExtendWith(MockitoExtension.class)
class PaymentStatusServiceTests {

    @InjectMocks
    private PaymentStatusService paymentStatusService;

    @Mock
    private PaymentStatusRepository paymentStatusRepository;

    @Mock
    private PaymentStatusMapper paymentStatusMapper;

    private PaymentStatus paymentStatus;

    @BeforeEach
    void setUp() {

        paymentStatus = PaymentStatus
                .builder()
                .id(1)
                .status(Status.Y)
                .dueDate(Date.valueOf("2024-07-15"))
                .build();

    }

    @Test
    @DisplayName("save all payment statuses")
    void whenSaveAll_thenReturnSavedPaymentStatuses() {

        var paymentStatusesForSave = Collections.singletonList(paymentStatus);

        doReturn(paymentStatusesForSave).when(paymentStatusRepository).saveAll(any());

        var savedPaymentStatuses = paymentStatusService.saveAll(paymentStatusesForSave);

        assertAll(
                () -> assertThat(savedPaymentStatuses).isNotEmpty(),
                () -> assertThat(savedPaymentStatuses).hasSameSizeAs(paymentStatusesForSave)
        );

        verify(paymentStatusRepository).saveAll(any());

    }

    @Test
    @DisplayName("find all payment statuses")
    void whenFindAll_thenReturnAllPaymentStatuses() {

        var paymentStatuses = Collections.singletonList(paymentStatus);

        doReturn(paymentStatuses).when(paymentStatusRepository).findAll();

        var savedPaymentStatuses = paymentStatusService.findAll();

        assertAll(
                () -> assertThat(savedPaymentStatuses).isNotEmpty(),
                () -> assertThat(savedPaymentStatuses).hasSameSizeAs(paymentStatuses)
        );

        verify(paymentStatusRepository).findAll();

    }

    @Test
    @DisplayName("save payment status")
    void whenSave_thenReturnSavedPaymentStatus() {

        doReturn(paymentStatus).when(paymentStatusRepository).save(any());

        var savedPaymentStatus = paymentStatusService.save(paymentStatus);

        assertAll(
                () -> assertThat(savedPaymentStatus).isNotNull(),
                () -> assertThat(savedPaymentStatus).isEqualTo(paymentStatus)
        );

        verify(paymentStatusRepository).save(any());

    }

    @Test
    @DisplayName("delete payment status by existing id")
    void whenDeleteById_thenReturnNothing() {

        when(paymentStatusRepository.findById(anyInt()))
                .thenReturn(Optional.of(paymentStatus))
                .thenReturn(Optional.empty());

        doNothing().when(paymentStatusRepository).delete(any());

        paymentStatusService.deleteById(paymentStatus.getId());

        assertThrows(EntityNotFoundException.class, () -> paymentStatusService.findById(paymentStatus.getId()));

        verify(paymentStatusRepository, times(2)).findById(anyInt());
        verify(paymentStatusRepository).delete(any());

    }

    @Test
    @DisplayName("delete payment status by not existing id")
    void whenDeleteById_thenThrowException() {

        when(paymentStatusRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> paymentStatusService.deleteById(paymentStatus.getId()));

        verify(paymentStatusRepository).findById(anyInt());
        verify(paymentStatusRepository, never()).delete(any());

    }

    @Test
    @DisplayName("find payment status by existing id")
    void whenFindById_thenReturnPaymentStatus() {

        doReturn(Optional.of(paymentStatus)).when(paymentStatusRepository).findById(anyInt());

        var savedPaymentStatus = paymentStatusService.findById(paymentStatus.getId());

        assertThat(savedPaymentStatus).isNotNull();

        verify(paymentStatusRepository).findById(anyInt());

    }

    @Test
    @DisplayName("find payment status by not existing id")
    void whenFindById_thenThrowException() {

        doReturn(Optional.empty()).when(paymentStatusRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> paymentStatusService.findById(paymentStatus.getId()));

        verify(paymentStatusRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update payment status by existing id")
    void whenUpdateById_thenReturnUpdatedPaymentStatus() {

        var persistedPaymentStatus = PaymentStatus
                .builder()
                .id(2)
                .status(Status.Y)
                .dueDate(Date.valueOf("2024-06-13"))
                .build();

        doReturn(Optional.of(persistedPaymentStatus)).when(paymentStatusRepository).findById(anyInt());

        persistedPaymentStatus.setStatus(paymentStatus.getStatus());
        persistedPaymentStatus.setDueDate(paymentStatus.getDueDate());

        doNothing().when(paymentStatusMapper).updatePaymentStatusPartial(any(), any());

        doReturn(persistedPaymentStatus).when(paymentStatusRepository).save(any());

        var updatedPaymentStatus = paymentStatusService.updateById(persistedPaymentStatus.getId(), paymentStatus);

        assertAll(
                () -> assertThat(updatedPaymentStatus.getStatus()).isEqualTo(paymentStatus.getStatus()),
                () -> assertThat(updatedPaymentStatus.getDueDate()).isEqualTo(paymentStatus.getDueDate())
        );

        verify(paymentStatusRepository).findById(anyInt());
        verify(paymentStatusMapper).updatePaymentStatusPartial(any(), any());
        verify(paymentStatusRepository).save(any());

    }

    @Test
    @DisplayName("update payment status by not existing id")
    void whenUpdateById_thenThrowException() {

        doReturn(Optional.empty()).when(paymentStatusRepository).findById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> paymentStatusService.updateById(2, paymentStatus));

        verify(paymentStatusRepository).findById(anyInt());

    }

    @Test
    @DisplayName("update or create payment status by existing id")
    void whenUpdateOrCreateById_thenReturnUpdatedPaymentStatus() {

        var persistedPaymentStatus = PaymentStatus
                .builder()
                .id(2)
                .status(Status.Y)
                .dueDate(Date.valueOf("2024-06-13"))
                .build();

        doReturn(Optional.of(persistedPaymentStatus)).when(paymentStatusRepository).findById(anyInt());

        persistedPaymentStatus.setStatus(paymentStatus.getStatus());
        persistedPaymentStatus.setDueDate(paymentStatus.getDueDate());

        doNothing().when(paymentStatusMapper).updatePaymentStatusPartial(any(), any());

        doReturn(persistedPaymentStatus).when(paymentStatusRepository).save(any());

        var updatedPaymentStatus = paymentStatusService.updateOrCreateById(persistedPaymentStatus.getId(), paymentStatus);

        assertAll(
                () -> assertThat(updatedPaymentStatus.getStatus()).isEqualTo(paymentStatus.getStatus()),
                () -> assertThat(updatedPaymentStatus.getDueDate()).isEqualTo(paymentStatus.getDueDate())
        );

        verify(paymentStatusRepository).findById(anyInt());
        verify(paymentStatusMapper).updatePaymentStatusPartial(any(), any());
        verify(paymentStatusRepository).save(any());

    }

    @Test
    @DisplayName("update or create payment status by not existing id")
    void wheUpdateOrCreateById_thenCreateNewPaymentStatus() {

        doReturn(Optional.empty()).when(paymentStatusRepository).findById(anyInt());

        doReturn(paymentStatus).when(paymentStatusRepository).save(any());

        var createdPaymentStatus = paymentStatusService.updateOrCreateById(2, paymentStatus);

        assertAll(
                () -> assertThat(createdPaymentStatus).isNotNull(),
                () -> assertThat(createdPaymentStatus).isEqualTo(paymentStatus)
        );

        verify(paymentStatusRepository).findById(anyInt());
        verify(paymentStatusRepository).save(any());

    }

}
