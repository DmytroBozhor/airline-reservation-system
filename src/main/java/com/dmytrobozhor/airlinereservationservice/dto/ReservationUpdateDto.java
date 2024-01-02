package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ReservationUpdateDto(

        @Valid
        PassengerDto passenger,

        @Valid
        SeatDetailDto seatDetail

) {
}
