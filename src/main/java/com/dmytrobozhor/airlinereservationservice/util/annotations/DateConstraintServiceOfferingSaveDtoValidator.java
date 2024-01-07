package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingSaveDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Timestamp;
import java.util.Optional;

public class DateConstraintServiceOfferingSaveDtoValidator implements ConstraintValidator<DateConstraint, ServiceOfferingSaveDto> {

    @Override
    public boolean isValid(ServiceOfferingSaveDto value, ConstraintValidatorContext context) {
        Optional<Timestamp> fromDate = Optional.ofNullable(value.formDate());
        Optional<Timestamp> toDate = Optional.ofNullable(value.toDate());
        if (fromDate.isEmpty() || toDate.isEmpty()) return true;
        return fromDate.get().getTime() < toDate.get().getTime();
    }
}
