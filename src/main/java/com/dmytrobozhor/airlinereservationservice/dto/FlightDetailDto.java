package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumValidator;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record FlightDetailDto(

        @NotNull
        Timestamp departureDateTime,

        @NotNull
        Timestamp arrivalDateTime,

//        TODO: somehow validate the enums
        @NotNull
        @EnumValidator(enumClass = AirplaneType.class)
        AirplaneType airplaneType,

//        TODO: replace with dto
        @NotNull
        Airport sourceAirport,

        @NotNull
        Airport destinationAirport

) {
}
