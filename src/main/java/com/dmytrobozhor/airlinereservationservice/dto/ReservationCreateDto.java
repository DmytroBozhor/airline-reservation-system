package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationCreateDto(

        @NotNull
        Long passengerId,

        @NotNull
        Long seatDetailId,

        @NotNull
        Timestamp reservationDateTime

) {
}
