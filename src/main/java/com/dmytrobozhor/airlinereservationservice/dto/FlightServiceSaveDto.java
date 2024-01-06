package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record FlightServiceSaveDto(

        @NotBlank
        @Length(max = 255)
        @EnumBasedString(enumClass = ServiceName.class)
        String serviceName

) {
}
