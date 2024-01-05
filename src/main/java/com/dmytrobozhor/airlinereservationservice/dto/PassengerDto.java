package com.dmytrobozhor.airlinereservationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PassengerDto(

        @NotBlank
        @Length(max = 255)
        String firstName,

        @NotBlank
        @Length(max = 255)
        String lastName,

        @Email
        @Length(max = 255)
        String email,

        @NotBlank
        @Length(min = 10, max = 10, message = "length must be 10")
        String phoneNumber,

        @Length(max = 255)
        String address,

        @Length(max = 255)
        String city,

        @Length(max = 255)
        String state,

        @Length(min = 5, max = 5, message = "length must be 5")
        String zipcode,

        @Length(max = 255)
        String country

) {
}
