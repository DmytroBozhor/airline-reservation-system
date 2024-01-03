package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FlightCostUpdateDto(

//        TODO: sort out things with EmbeddedId
        @Valid
        SeatDetailDto seatDetail,

//        TODO: validate that validFromDate < validToDate
        @Valid
        CalendarDto validFromDate,

        @Valid
        CalendarDto validToDate,

        BigDecimal cost

) {
}
