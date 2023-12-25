package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    Airport toAirport(AirportCreateDto airportCreateDto);

}
