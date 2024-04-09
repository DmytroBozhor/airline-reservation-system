package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightCost;
import com.dmytrobozhor.airlinereservationservice.repository.FlightCostRepository;
import com.dmytrobozhor.airlinereservationservice.service.base.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.flightcost.FlightCostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class FlightCostService extends ServiceBase<FlightCost, Long> {

    private final FlightCostRepository flightCostRepository;

    private final FlightCostMapper flightCostMapper;

    @Autowired
    public FlightCostService(FlightCostRepository flightCostRepository, FlightCostMapper flightCostMapper) {
        super(flightCostRepository, flightCostMapper);
        this.flightCostRepository = flightCostRepository;
        this.flightCostMapper = flightCostMapper;
    }
}
