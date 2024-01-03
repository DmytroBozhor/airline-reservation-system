package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Timestamp;
import java.util.Optional;

public class DateConstraintFlightDetailValidator implements ConstraintValidator<DateConstraint, FlightDetailDto> {

    @Override
    public boolean isValid(FlightDetailDto value, ConstraintValidatorContext context) {
        Optional<Timestamp> departureTimestamp = Optional.ofNullable(value.departureDateTime());
        Optional<Timestamp> arrivalTimestamp = Optional.ofNullable(value.arrivalDateTime());
        if (departureTimestamp.isEmpty() || arrivalTimestamp.isEmpty()) return true;
        return departureTimestamp.get().getTime() < arrivalTimestamp.get().getTime();
    }

}
