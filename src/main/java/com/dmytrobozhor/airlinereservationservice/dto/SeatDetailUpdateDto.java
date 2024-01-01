package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;

public record SeatDetailUpdateDto(

        @Valid
        TravelClassDto travelClass,

        @Valid
        FlightDetailDto flightDetail

) {
}
