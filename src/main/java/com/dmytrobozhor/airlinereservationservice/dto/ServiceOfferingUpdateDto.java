package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

// TODO: check if multiple validations work for the annotation below
@DateConstraint(message = "the fromDate should be less than the toDate")
public record ServiceOfferingUpdateDto(

        Boolean offered,

        Timestamp formDate,

        Timestamp toDate,
// TODO: find out if update dto is needed instead of a regular one
        @Valid
        TravelClassDto travelClass,

        @Valid
        FlightServiceDto flightService

) {
}
