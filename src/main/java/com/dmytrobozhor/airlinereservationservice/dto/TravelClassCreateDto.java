package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import jakarta.validation.constraints.NotNull;

public record TravelClassCreateDto(

//        @NotBlank
//        @Length(max = 255)
//        @EnumBasedString(enumClass = TravelClassName.class)
        @NotNull
        TravelClassName name,

        @NotNull
        Integer capacity

) {
}
