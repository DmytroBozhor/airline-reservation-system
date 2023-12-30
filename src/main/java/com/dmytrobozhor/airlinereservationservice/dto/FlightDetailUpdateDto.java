package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.AirportConstraint;
import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@DateConstraint
@AirportConstraint
public record FlightDetailUpdateDto(

        Timestamp departureDateTime,

        Timestamp arrivalDateTime,

        @EnumBasedString(enumClass = AirplaneType.class)
        String airplaneType,

        @Valid
        AirportDto sourceAirport,

        @Valid
        AirportDto destinationAirport

) {
}
