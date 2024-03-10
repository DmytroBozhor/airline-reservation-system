package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

public record SeatDetailCreateDto(

        @NotNull
        Long travelClassId,

        @NotNull
        Long flightDetailId

) {
}
