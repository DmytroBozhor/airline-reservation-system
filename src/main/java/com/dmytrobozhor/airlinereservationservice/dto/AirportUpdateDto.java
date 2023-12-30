package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportUpdateDto(

        @Size(min = 1, max = 255)
        String name,

        @Size(min = 1, max = 255)
        String city,

        @Size(min = 1, max = 255)
        String country

) {
}
