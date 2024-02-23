package com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail.FlightDetailMappingConstants.*;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = SOURCE_AIRPORT, target = SOURCE_AIRPORT_ID)
@Mapping(source = DESTINATION_AIRPORT, target = DESTINATION_AIRPORT_ID)
public @interface AssociationFromEntityToId {
}
