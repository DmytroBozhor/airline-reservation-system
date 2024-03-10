package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

//@DateConstraint
//@AirportConstraint
public record FlightDetailCreateDto(

        @NotNull
        Timestamp departureDateTime,

        @NotNull
        Timestamp arrivalDateTime,

        //TODO: * make the value of the airplaneType to uppercase using annotation and beanPostProcessor
//        @NotBlank
        @NotNull
//        @Length(max = 255)
//        @EnumBasedString(enumClass = AirplaneType.class)
        AirplaneType airplaneType,

        @NotNull
        Long sourceAirportId,

        @NotNull
        Long destinationAirportId

) {
}
