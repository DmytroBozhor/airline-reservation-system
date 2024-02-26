package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import com.dmytrobozhor.airlinereservationservice.service.service.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail.FlightDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class FlightDetailService extends ServiceBase<FlightDetail, Long> {

    private final FlightDetailRepository flightDetailRepository;

    private final FlightDetailMapper flightDetailMapper;

    @Autowired
    public FlightDetailService(FlightDetailRepository flightDetailRepository, FlightDetailMapper flightDetailMapper){
        super(flightDetailRepository, flightDetailMapper);
        this.flightDetailRepository = flightDetailRepository;
        this.flightDetailMapper = flightDetailMapper;
    }
}
