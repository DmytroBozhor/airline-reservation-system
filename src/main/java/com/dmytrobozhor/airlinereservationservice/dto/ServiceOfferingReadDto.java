package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@DateConstraint(message = "the from date should be less than the to date")
public record ServiceOfferingReadDto(

        @NotNull
        Long id,

        @NotNull
        Boolean offered,

        Timestamp fromDate,

        Timestamp toDate,

        @NotNull
        Long travelClassId,

        @NotNull
        Long flightServiceId

) {
}
