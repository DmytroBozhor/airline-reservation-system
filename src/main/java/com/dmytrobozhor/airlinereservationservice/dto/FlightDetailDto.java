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
public record FlightDetailDto(

        @NotNull
        Timestamp departureDateTime,

        @NotNull
        Timestamp arrivalDateTime,

        //TODO: * make the value of the airplaneType to uppercase using annotation and beanPostProcessor
        @NotNull
        @EnumBasedString(enumClass = AirplaneType.class)
        String airplaneType,

        @NotNull
        @Valid
        AirportDto sourceAirport,

        @NotNull
        @Valid
        AirportDto destinationAirport

) {
}
