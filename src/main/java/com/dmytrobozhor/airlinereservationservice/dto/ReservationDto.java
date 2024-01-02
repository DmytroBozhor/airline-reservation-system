package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.Instant;

public record ReservationDto(

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
