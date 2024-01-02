package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.sql.Date;

public record PaymentStatusUpdateDto(

        @Length(max = 1)
        @EnumBasedString(enumClass = Status.class)
        String status,

        Date dueDate,

        BigDecimal amount,

        @Valid
        ReservationDto reservation

) {
}
