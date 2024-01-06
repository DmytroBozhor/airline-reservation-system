package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SeatDetailSaveDto(

        @NotNull
        @Valid
        TravelClassDto travelClass,

        @NotNull
        @Valid
        FlightDetailDto flightDetail

) {
}
