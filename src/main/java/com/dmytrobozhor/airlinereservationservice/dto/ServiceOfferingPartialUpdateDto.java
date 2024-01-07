package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;
import jakarta.validation.Valid;

import java.sql.Timestamp;

@DateConstraint(message = "the fromDate should be less than the toDate")
public record ServiceOfferingPartialUpdateDto(

        Boolean offered,

        Timestamp formDate,

        Timestamp toDate

) {
}
