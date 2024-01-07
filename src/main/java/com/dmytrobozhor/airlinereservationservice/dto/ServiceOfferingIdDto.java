package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

public record ServiceOfferingIdDto(

        @NotNull
        Integer travelClassId,

        @NotNull
        Integer flightServiceId

) {
}
