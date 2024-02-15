package com.dmytrobozhor.airlinereservationservice.service.entity.manager;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;

public interface AbstractFlightDetailServiceEM {

    FlightDetail save(FlightDetail flightDetail);

    FlightDetail update(FlightDetail flightDetail);

}
