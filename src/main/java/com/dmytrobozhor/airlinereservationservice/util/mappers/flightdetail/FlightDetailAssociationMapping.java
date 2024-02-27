package com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail.FlightDetailMappingConstants.*;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = SOURCE_AIRPORT_ID, target = SOURCE_AIRPORT)
@Mapping(source = DESTINATION_AIRPORT_ID, target = DESTINATION_AIRPORT)
public @interface FlightDetailAssociationMapping {
}
