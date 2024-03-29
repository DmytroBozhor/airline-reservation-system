package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;

import java.sql.Timestamp;

public record ReservationPartialUpdateDto(

        Long passengerId,

        Long seatDetailId,

        Timestamp reservationDateTime

) {
}
