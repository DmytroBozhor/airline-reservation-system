package com.dmytrobozhor.airlinereservationservice.exceptions;

import java.io.Serial;

public class EntityUniquePhoneNumberException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5150814488484398651L;

    public EntityUniquePhoneNumberException() {
        super("Passenger with such a phone number already exists");
    }

    public EntityUniquePhoneNumberException(String message) {
        super(message);
    }
}
