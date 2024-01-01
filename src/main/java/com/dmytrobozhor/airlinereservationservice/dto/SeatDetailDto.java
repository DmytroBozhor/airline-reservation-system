package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SeatDetailDto(

        @NotNull
        @Valid
        TravelClassDto travelClass,

        @NotNull
        @Valid
        FlightDetailDto flightDetail

) {
}
