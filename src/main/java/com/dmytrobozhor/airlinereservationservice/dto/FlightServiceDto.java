package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record FlightServiceDto(

        @NotBlank
        @Length(max = 255)
        @EnumBasedString(enumClass = ServiceName.class)
        String serviceName,

        @Valid
        List<TravelClassDto> travelClasses

) {
}

/*create table if not exists "flight_service"
(
    "id"           serial,
    "service_name" varchar(255) not null,
    constraint "flight_service_id_pk" primary key ("id"),
    constraint "service_name_check" check ( "service_name" in ('FOOD', 'FRENCH_WINE', 'WIFI', 'ENTERTAINMENT', 'LOUNGE'))
);*/