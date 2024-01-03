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
        DateConstraintFlightDetailValidator.class,
        DateConstraintServiceOfferingValidator.class})
public @interface DateConstraint {

    String message() default "the departure date should be less than arrival date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
