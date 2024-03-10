package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.repository.SeatDetailRepository;
import com.dmytrobozhor.airlinereservationservice.service.base.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.seatdetail.SeatDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class SeatDetailService extends ServiceBase<SeatDetail, Long> {

    private final SeatDetailRepository seatDetailRepository;

    private final SeatDetailMapper seatDetailMapper;

    @Autowired
    public SeatDetailService(SeatDetailRepository seatDetailRepository, SeatDetailMapper seatDetailMapper) {
        super(seatDetailRepository, seatDetailMapper);
        this.seatDetailRepository = seatDetailRepository;
        this.seatDetailMapper = seatDetailMapper;
    }
}
