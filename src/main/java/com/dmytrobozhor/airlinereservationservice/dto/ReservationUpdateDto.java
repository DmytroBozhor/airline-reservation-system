package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationUpdateDto(

        @Valid
        PassengerDto passenger,

        @Valid
        SeatDetailDto seatDetail,

        Timestamp reservationDateTime

) {
}
