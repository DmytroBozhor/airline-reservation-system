package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.repository.FlightServiceRepository;
import com.dmytrobozhor.airlinereservationservice.service.base.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.flightservice.FlightServiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class FlightServiceService extends ServiceBase<FlightService, Long> {

    private final FlightServiceRepository flightServiceRepository;

    private final FlightServiceMapper flightServiceMapper;

    @Autowired
    public FlightServiceService(FlightServiceRepository flightServiceRepository, FlightServiceMapper flightServiceMapper) {
        super(flightServiceRepository, flightServiceMapper);
        this.flightServiceRepository = flightServiceRepository;
        this.flightServiceMapper = flightServiceMapper;
    }
}
