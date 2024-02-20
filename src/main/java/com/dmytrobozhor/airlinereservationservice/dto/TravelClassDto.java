package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record TravelClassDto(

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
