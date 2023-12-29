package com.dmytrobozhor.airlinereservationservice.util.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumBasedStringValidator implements ConstraintValidator<EnumBasedString, String> {

    private Set<String> values;

    @Override
    public void initialize(EnumBasedString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<String> optionalValue = Optional.ofNullable(value);
        if (optionalValue.isEmpty()) return true;
        return values.contains(optionalValue.get());
    }

}
