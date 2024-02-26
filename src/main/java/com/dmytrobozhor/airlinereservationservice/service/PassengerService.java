package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.repository.PassengerRepository;
import com.dmytrobozhor.airlinereservationservice.service.service.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.passenger.PassengerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PassengerService extends ServiceBase<Passenger, Long> {

    private final PassengerRepository passengerRepository;

    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, PassengerMapper passengerMapper){
        super(passengerRepository, passengerMapper);
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }
}
