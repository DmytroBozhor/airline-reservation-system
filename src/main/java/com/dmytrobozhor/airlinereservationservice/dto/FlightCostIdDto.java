package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record FlightCostIdDto(

        @NotNull
        Integer seatDetailId,

        @NotNull
        Date validFromDateId

) {
}
