package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FlightCostReadDto(

        @NotNull
        Long id,

        @NotNull
        Long seatDetail,

//        TODO: validate that validFromDate < validToDate
        @NotNull
        Long validFromDate,

        @NotNull
        Long validToDate,

        @NotNull
        BigDecimal cost

) {
}
