package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;

import java.sql.Timestamp;

public record ReservationPartialUpdateDto(

        @Valid
        PassengerDto passenger,

        @Valid
        SeatDetailDto seatDetail,

        Timestamp reservationDateTime

) {
}
