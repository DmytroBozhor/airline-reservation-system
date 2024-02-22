package com.dmytrobozhor.airlinereservationservice.util.mappers.flight;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = "departureDateTime", target = "departureDateTime")
@Mapping(source = "arrivalDateTime", target = "arrivalDateTime")
@Mapping(source = "airplaneType", target = "airplaneType")
@Mapping(source = "sourceAirportId", target = "sourceAirport")
@Mapping(source = "destinationAirportId", target = "destinationAirport")
public @interface FlightDetailBaseMapping {
}
