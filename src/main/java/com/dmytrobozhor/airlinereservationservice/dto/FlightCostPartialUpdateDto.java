package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;

import java.math.BigDecimal;

public record FlightCostPartialUpdateDto(

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
