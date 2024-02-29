package com.dmytrobozhor.airlinereservationservice.util.mappers.reservation;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.reservation.ReservationMappingConstants.*;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = PASSENGER_ID, target = PASSENGER)
@Mapping(source = SEAT_DETAIL_ID, target = SEAT_DETAIL)
public @interface ReservationAssociationMapper {
}
