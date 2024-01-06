package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;

public interface AbstractFlightDetailService extends AbstractCrudService<FlightDetail, Integer> {

    void fetchDataIfExist(FlightDetail flightDetail);

}
