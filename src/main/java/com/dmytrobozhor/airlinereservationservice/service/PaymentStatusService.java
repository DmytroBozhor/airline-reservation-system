package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.PaymentStatus;
import com.dmytrobozhor.airlinereservationservice.repository.PaymentStatusRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PaymentStatusMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentStatusService implements AbstractPaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;

    private final PaymentStatusMapper paymentStatusMapper;

    @Override
    public List<PaymentStatus> findAll() {
        return paymentStatusRepository.findAll();
    }

    @Override
    public PaymentStatus save(PaymentStatus paymentStatus) {
        return paymentStatusRepository.save(paymentStatus);
    }

    @Override
    public void deleteById(Integer id) {
        PaymentStatus paymentStatus = paymentStatusRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        paymentStatusRepository.delete(paymentStatus);
    }

    @Override
    public void delete(PaymentStatus paymentStatus) {
        PaymentStatus persistedPaymentStatus = paymentStatusRepository
                .findByAllFields(paymentStatus).orElseThrow(EntityNotFoundException::new);
        paymentStatusRepository.delete(persistedPaymentStatus);
    }

    @Override
    public PaymentStatus findById(Integer id) {
        return paymentStatusRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public PaymentStatus updateById(Integer id, PaymentStatus paymentStatus) {
        return paymentStatusRepository.findById(id).map(persistedPaymentStatus -> {
            paymentStatusMapper.updatePaymentStatusPartial(persistedPaymentStatus, paymentStatus);
            return paymentStatusRepository.save(persistedPaymentStatus);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public PaymentStatus updateOrCreateById(Integer id, PaymentStatus paymentStatus) {
        return paymentStatusRepository.findById(id).map(persistedPaymentStatus -> {
            paymentStatusMapper.updatePaymentStatusPartial(persistedPaymentStatus, paymentStatus);
            return paymentStatusRepository.save(persistedPaymentStatus);
        }).orElse(paymentStatusRepository.save(paymentStatus));
    }
}
