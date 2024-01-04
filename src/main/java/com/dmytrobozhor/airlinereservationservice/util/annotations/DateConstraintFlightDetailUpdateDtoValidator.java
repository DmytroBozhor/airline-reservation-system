package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailUpdateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Timestamp;
import java.util.Optional;

public class DateConstraintFlightDetailUpdateDtoValidator implements ConstraintValidator<DateConstraint, FlightDetailUpdateDto> {

    @Override
    public boolean isValid(FlightDetailUpdateDto value, ConstraintValidatorContext context) {
        Optional<Timestamp> departureTimestamp = Optional.ofNullable(value.departureDateTime());
        Optional<Timestamp> arrivalTimestamp = Optional.ofNullable(value.arrivalDateTime());
        if (departureTimestamp.isEmpty() || arrivalTimestamp.isEmpty()) return true;
        return departureTimestamp.get().getTime() < arrivalTimestamp.get().getTime();
    }

}
