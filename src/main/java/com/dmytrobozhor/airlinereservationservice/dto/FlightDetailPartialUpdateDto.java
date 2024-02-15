package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.AirportConstraint;
import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

//@DateConstraint
//@AirportConstraint
public record FlightDetailPartialUpdateDto(

        Timestamp departureDateTime,

        Timestamp arrivalDateTime,

//        @Length(max = 255)
//        @EnumBasedString(enumClass = AirplaneType.class)
        AirplaneType airplaneType,

        @Valid
        AirportDto sourceAirport,

        @Valid
        AirportDto destinationAirport

) {
}
