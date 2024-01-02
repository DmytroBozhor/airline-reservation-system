package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PassengerCreateDto(

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
        @Length(min = 10, max = 10)
        @Unique
        String phoneNumber,

        @Length(max = 255)
        String address,

        @Length(max = 255)
        String city,

        @Length(max = 255)
        String state,

        @Length(min = 5, max = 5)
        String zipcode,

        @Length(max = 255)
        String country

) {
}
