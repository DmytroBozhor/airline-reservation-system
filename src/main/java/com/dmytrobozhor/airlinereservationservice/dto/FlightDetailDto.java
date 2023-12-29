package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedStringValidator;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record FlightDetailDto(

//        TODO: make constraints as like in database
        @NotNull
        Timestamp departureDateTime,

        @NotNull
        Timestamp arrivalDateTime,

        //TODO: make the value of the airplaneType to uppercase using annotation and beanPostProcessor
        @NotNull
        @EnumBasedStringValidator(enumClass = AirplaneType.class)
        String airplaneType,

//        TODO: if airport already exists then use existing and not inserting a new one to the database
        @NotNull
        @Valid
        AirportDto sourceAirport,

        @NotNull
        @Valid
        AirportDto destinationAirport

) {
}
