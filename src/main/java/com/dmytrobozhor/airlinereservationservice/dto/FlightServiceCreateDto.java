package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record FlightServiceCreateDto(

        @NotBlank
        @Length(max = 255)
        String serviceName,

        @NotNull
        BigDecimal cost

) {
}
