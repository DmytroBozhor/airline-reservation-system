package com.dmytrobozhor.airlinereservationservice.dto;

import java.math.BigDecimal;

public record FlightServicePartialUpdateDto(

        String serviceName,

        BigDecimal cost

) {
}
