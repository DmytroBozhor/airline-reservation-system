package com.dmytrobozhor.airlinereservationservice.dto;

import com.dmytrobozhor.airlinereservationservice.util.annotations.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PassengerUpdateDto(

        String firstName,

        String lastName,

        @Email
        String email,

        @Length(min = 10, max = 10)
        @Unique
        String phoneNumber,

        String address,

        String city,

        String state,

        @Length(min = 5, max = 5)
        String zipcode,

        String country

) {
}
