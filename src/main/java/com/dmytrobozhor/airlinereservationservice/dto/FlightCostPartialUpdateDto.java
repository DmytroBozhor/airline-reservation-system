package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;

import java.math.BigDecimal;

public record FlightCostPartialUpdateDto(

//        TODO: validate that validFromDate < validToDate
        @Valid
        CalendarDto validToDate,

        BigDecimal cost

) {
}
