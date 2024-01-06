package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;

public interface AbstractSeatDetailService extends AbstractCrudService<SeatDetail, Integer> {

    void fetchDataIfExist(SeatDetail seatDetail);

}
