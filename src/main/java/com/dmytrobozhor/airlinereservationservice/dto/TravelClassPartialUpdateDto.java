package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import org.hibernate.validator.constraints.Length;

public record TravelClassPartialUpdateDto(

//        @Length(max = 255)
//        @EnumBasedString(enumClass = TravelClassName.class)
        TravelClassName name,

        Integer capacity

) {
}
