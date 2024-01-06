package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailSaveDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class AirportConstraintSaveDtoValidator implements ConstraintValidator<AirportConstraint, FlightDetailSaveDto> {

    @Override
    public boolean isValid(FlightDetailSaveDto value, ConstraintValidatorContext context) {
        Optional<AirportDto> sourceAirport = Optional.ofNullable(value.sourceAirport());
        Optional<AirportDto> destinationAirport = Optional.ofNullable(value.destinationAirport());
        if (sourceAirport.isEmpty() || destinationAirport.isEmpty()) return true;
        return !sourceAirport.get().equals(destinationAirport.get());
    }

}
