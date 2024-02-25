package com.dmytrobozhor.airlinereservationservice.util.mappers.passenger;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.passenger.PassengerMappingConstants.*;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = PERSONAL_INFO + FIRST_NAME, source = FIRST_NAME)
@Mapping(target = PERSONAL_INFO + LAST_NAME, source = LAST_NAME)
@Mapping(target = PERSONAL_INFO + PHONE_NUMBER, source = PHONE_NUMBER)
@Mapping(target = ADDITIONAL_INFO + EMAIL, source = EMAIL)
@Mapping(target = ADDITIONAL_INFO + ADDRESS, source = ADDRESS)
@Mapping(target = ADDITIONAL_INFO + CITY, source = CITY)
@Mapping(target = ADDITIONAL_INFO + STATE, source = STATE)
@Mapping(target = ADDITIONAL_INFO + ZIPCODE, source = ZIPCODE)
@Mapping(target = ADDITIONAL_INFO + COUNTRY, source = COUNTRY)
public @interface PassengerEmbeddedComponentsMapping {
}
