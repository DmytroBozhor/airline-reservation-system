package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@DateConstraint(message = "the from date should be less than the to date")
public record ServiceOfferingSaveDto(

        @NotNull
        Boolean offered,

        Timestamp formDate,

        Timestamp toDate,

        @NotNull
        @Valid
        TravelClassDto travelClass,

        @NotNull
        @Valid
        FlightServiceDto flightService

) {
}
