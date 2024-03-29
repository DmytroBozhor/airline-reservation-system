package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.EnumBasedString;
import com.dmytrobozhor.airlinereservationservice.util.enums.Status;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.sql.Date;

public record PaymentStatusPartialUpdateDto(

//        @Length(max = 1)
//        @EnumBasedString(enumClass = Status.class)
        String status,

        Date dueDate,

        BigDecimal amount,

        Long reservationId

) {
}
