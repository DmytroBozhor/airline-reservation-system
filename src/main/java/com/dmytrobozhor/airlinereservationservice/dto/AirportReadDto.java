package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AirportReadDto(

        @NotNull
        Long id,

        @NotBlank
        @Length(max = 255)
        String name,

        @NotBlank
        @Length(max = 255)
        String city,

        @NotBlank
        @Length(max = 255)
        String country

) {
}
