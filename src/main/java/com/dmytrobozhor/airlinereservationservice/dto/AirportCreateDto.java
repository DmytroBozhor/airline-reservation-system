package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportCreateDto(

        @NotBlank
        @Size(min = 1, max = 255)
        String name,

        @NotBlank
        @Size(min = 1, max = 255)
        String city,

        @NotBlank
        @Size(min = 1, max = 255)
        String country

) {
}
