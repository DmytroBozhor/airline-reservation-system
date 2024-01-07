package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingSaveDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Timestamp;
import java.util.Optional;

public class DateConstraintServiceOfferingUpdateDtoValidator
        implements ConstraintValidator<DateConstraint, ServiceOfferingPartialUpdateDto> {

    @Override
    public boolean isValid(ServiceOfferingPartialUpdateDto value, ConstraintValidatorContext context) {
        Optional<Timestamp> fromDate = Optional.ofNullable(value.formDate());
        Optional<Timestamp> toDate = Optional.ofNullable(value.toDate());
        if (fromDate.isEmpty() || toDate.isEmpty()) return true;
        return fromDate.get().getTime() < toDate.get().getTime();
    }
}
