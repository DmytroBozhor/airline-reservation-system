package com.dmytrobozhor.airlinereservationservice.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumBasedStringValidator.class)
public @interface EnumBasedString {

    Class<? extends Enum<?>> enumClass();

    String message() default "must be a constant of a required type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
