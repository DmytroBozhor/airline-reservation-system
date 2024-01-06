package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationSaveDto(

        @NotNull
        @Valid
        PassengerDto passenger,

        @NotNull
        @Valid
        SeatDetailDto seatDetail,

        @NotNull
        Timestamp reservationDateTime

) {
}
