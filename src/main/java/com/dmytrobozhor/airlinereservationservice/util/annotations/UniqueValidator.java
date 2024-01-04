package com.dmytrobozhor.airlinereservationservice.util.annotations;

import com.dmytrobozhor.airlinereservationservice.service.PassengerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final PassengerService passengerService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<String> valueOptional = Optional.ofNullable(value);
        return valueOptional.map(s -> passengerService
                .findByPhoneNumber(value).isEmpty()).orElse(true);
    }

}
