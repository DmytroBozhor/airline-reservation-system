package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;

public record SeatDetailPartialUpdateDto(

        @Valid
        TravelClassDto travelClass,

        @Valid
        FlightDetailDto flightDetail

) {
}
