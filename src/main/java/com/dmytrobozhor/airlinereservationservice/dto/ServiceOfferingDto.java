package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

// TODO: check if multiple validations work for the annotation below
@DateConstraint(message = "the from date should be less than the to date")
public record ServiceOfferingDto(

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
