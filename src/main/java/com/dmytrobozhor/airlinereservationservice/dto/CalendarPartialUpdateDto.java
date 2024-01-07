package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record CalendarPartialUpdateDto(

        Date date,

        Boolean businessDay

) {
}
