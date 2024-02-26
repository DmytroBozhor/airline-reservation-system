package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import com.dmytrobozhor.airlinereservationservice.repository.ReservationRepository;
import com.dmytrobozhor.airlinereservationservice.service.service.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ReservationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class ReservationService extends ServiceBase<Reservation, Long> {

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        super(reservationRepository, reservationMapper);
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }
}
