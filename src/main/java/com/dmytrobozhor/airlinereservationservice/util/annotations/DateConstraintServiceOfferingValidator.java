package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class DateConstraintServiceOfferingValidator implements ConstraintValidator<DateConstraint, ServiceOfferingDto> {

    //   TODO: generalize the annotation and validators using reflection api
    @Override
    public boolean isValid(ServiceOfferingDto value, ConstraintValidatorContext context) {
        Optional<Timestamp> fromDate = Optional.ofNullable(value.formDate());
        Optional<Timestamp> toDate = Optional.ofNullable(value.toDate());
        if (fromDate.isEmpty() || toDate.isEmpty()) return true;
        return fromDate.get().getTime() < toDate.get().getTime();
    }
}
