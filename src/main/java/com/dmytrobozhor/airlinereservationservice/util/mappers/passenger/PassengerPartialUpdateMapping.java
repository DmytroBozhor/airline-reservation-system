package com.dmytrobozhor.airlinereservationservice.util.mappers.passenger;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.passenger.PassengerMappingConstants.*;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = PERSONAL_INFO + FIRST_NAME, source = PERSONAL_INFO + FIRST_NAME)
@Mapping(target = PERSONAL_INFO + LAST_NAME, source = PERSONAL_INFO + LAST_NAME)
@Mapping(target = PERSONAL_INFO + PHONE_NUMBER, source = PERSONAL_INFO + PHONE_NUMBER)
@Mapping(target = ADDITIONAL_INFO + EMAIL, source = ADDITIONAL_INFO + EMAIL)
@Mapping(target = ADDITIONAL_INFO + ADDRESS, source = ADDITIONAL_INFO + ADDRESS)
@Mapping(target = ADDITIONAL_INFO + CITY, source = ADDITIONAL_INFO + CITY)
@Mapping(target = ADDITIONAL_INFO + STATE, source = ADDITIONAL_INFO + STATE)
@Mapping(target = ADDITIONAL_INFO + ZIPCODE, source = ADDITIONAL_INFO + ZIPCODE)
@Mapping(target = ADDITIONAL_INFO + COUNTRY, source = ADDITIONAL_INFO + COUNTRY)
public @interface PassengerPartialUpdateMapping {
}
