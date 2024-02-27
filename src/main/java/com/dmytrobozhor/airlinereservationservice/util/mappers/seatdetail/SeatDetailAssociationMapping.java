package com.dmytrobozhor.airlinereservationservice.util.mappers.seatdetail;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.seatdetail.SeatDetailMappingConstants.*;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = TRAVEL_CLASS_ID, target = TRAVEL_CLASS)
@Mapping(source = FLIGHT_DETAIL_ID, target = FLIGHT_DETAIL)
public @interface SeatDetailAssociationMapping {
}
