package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.DateConstraint;

import java.sql.Timestamp;

@DateConstraint(message = "the fromDate should be less than the toDate")
public record ServiceOfferingPartialUpdateDto(

        Boolean offered,

        Timestamp fromDate,

        Timestamp toDate,

        Long travelClassId,

        Long flightServiceId

) {
}
