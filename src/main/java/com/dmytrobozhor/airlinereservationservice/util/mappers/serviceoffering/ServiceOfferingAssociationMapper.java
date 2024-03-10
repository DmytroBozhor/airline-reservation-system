package com.dmytrobozhor.airlinereservationservice.util.mappers.serviceoffering;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = "travelClassId", target = "travelClass")
@Mapping(source = "flightServiceId", target = "flightService")
public @interface ServiceOfferingAssociationMapper {
}
