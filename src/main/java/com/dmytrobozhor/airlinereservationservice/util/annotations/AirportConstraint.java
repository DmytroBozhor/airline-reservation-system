package com.dmytrobozhor.airlinereservationservice.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        AirportConstraintValidator.class,
        AirportConstraintUpdateDtoValidator.class})
public @interface AirportConstraint {

    String message() default "the source and destination airports must differ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
