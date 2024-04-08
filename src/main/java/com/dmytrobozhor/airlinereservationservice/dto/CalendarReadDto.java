package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record CalendarReadDto(

        @NotNull
        Long id,

        @NotNull
        Date date,

        @NotNull
        Boolean businessDay

) {
}
