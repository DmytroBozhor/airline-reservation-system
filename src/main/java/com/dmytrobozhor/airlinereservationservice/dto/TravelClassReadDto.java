package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import jakarta.validation.constraints.NotNull;

public record TravelClassReadDto(

        @NotNull
        Long id,

        @NotNull
//        @NotBlank
//        @Length(max = 255)
//        @EnumBasedString(enumClass = TravelClassName.class)
        TravelClassName name,

        @NotNull
        Integer capacity

) {
}
