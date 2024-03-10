package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.TravelClassRepository;
import com.dmytrobozhor.airlinereservationservice.service.base.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.travelclass.TravelClassMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TravelClassService extends ServiceBase<TravelClass, Long> {

    private final TravelClassRepository travelClassRepository;

    private final TravelClassMapper travelClassMapper;

    @Autowired
    public TravelClassService(TravelClassRepository travelClassRepository, TravelClassMapper travelClassMapper) {
        super(travelClassRepository, travelClassMapper);
        this.travelClassRepository = travelClassRepository;
        this.travelClassMapper = travelClassMapper;
    }
}
