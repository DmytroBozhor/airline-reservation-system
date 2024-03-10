package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

public record SeatDetailReadDto(

        @NotNull
        Long id,

        @NotNull
        Long travelClassId,

        @NotNull
        Long flightDetailId

) {
}
