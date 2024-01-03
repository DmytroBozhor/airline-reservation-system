package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record CalendarDto(

//        TODO: date - id. decide whether or not a unique annotation should be added.
//         or just leave it to the database
        @NotNull
        Date date,

        @NotNull
        Boolean businessDay

) {
}
