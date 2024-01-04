package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailUpdateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class AirportConstraintUpdateDtoValidator implements ConstraintValidator<AirportConstraint, FlightDetailUpdateDto> {

    @Override
    public boolean isValid(FlightDetailUpdateDto value, ConstraintValidatorContext context) {
        Optional<AirportDto> sourceAirport = Optional.ofNullable(value.sourceAirport());
        Optional<AirportDto> destinationAirport = Optional.ofNullable(value.destinationAirport());
        if (sourceAirport.isEmpty() || destinationAirport.isEmpty()) return true;
        return !sourceAirport.get().equals(destinationAirport.get());
    }

}
