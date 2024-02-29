package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationReadDto(

        @NotNull
        Long id,

        @NotNull
        Long passengerId,

        @NotNull
        Long seatDetailId,

        @NotNull
        Timestamp reservationDateTime

) {
}
