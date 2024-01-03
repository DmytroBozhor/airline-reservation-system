package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FlightCostDto(

        @NotNull
        @Valid
        SeatDetailDto seatDetail,

//        TODO: validate that validFromDate < validToDate
        @NotNull
        @Valid
        CalendarDto validFromDate,

        @NotNull
        @Valid
        CalendarDto validToDate,

        @NotNull
        BigDecimal cost

) {
}
