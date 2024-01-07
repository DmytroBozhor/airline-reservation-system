package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import com.dmytrobozhor.airlinereservationservice.util.compositeid.ServiceOfferingId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@DateConstraint(message = "the from date should be less than the to date")
public record ServiceOfferingDto(

        @NotNull
        @Valid
        ServiceOfferingIdDto id,

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
