package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FlightCostPartialUpdateDto(

        Long seatDetail,

        Long validFromDate,

        Long validToDate,

        BigDecimal cost

) {
}
