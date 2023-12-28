package com.dmytrobozhor.airlinereservationservice.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidatorConstraint.class)
public @interface EnumBasedStringValidator {

    Class<? extends Enum<?>> enumClass();

    String message() default "must be a constant of a required type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
