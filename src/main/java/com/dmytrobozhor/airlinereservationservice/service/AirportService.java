package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.service.base.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.airport.AirportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AirportService extends ServiceBase<Airport, Long> {

    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

    @Autowired
    public AirportService(AirportRepository airportRepository, AirportMapper airportMapper){
        super(airportRepository, airportMapper);
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }
}
