package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.PaymentStatus;
import com.dmytrobozhor.airlinereservationservice.repository.PaymentStatusRepository;
import com.dmytrobozhor.airlinereservationservice.service.service.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.paymentstatus.PaymentStatusMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class PaymentStatusService extends ServiceBase<PaymentStatus, Long> {

    private final PaymentStatusRepository paymentStatusRepository;

    private final PaymentStatusMapper paymentStatusMapper;

    @Autowired
    public PaymentStatusService(PaymentStatusRepository paymentStatusRepository, PaymentStatusMapper paymentStatusMapper) {
        super(paymentStatusRepository, paymentStatusMapper);
        this.paymentStatusRepository = paymentStatusRepository;
        this.paymentStatusMapper = paymentStatusMapper;
    }
}
