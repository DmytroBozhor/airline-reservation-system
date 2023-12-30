package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    Airport toAirport(AirportDto airportDto);

    Airport toAirport(AirportUpdateDto airportDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAirportPartial(@MappingTarget Airport persistedAirport, Airport airport);

    List<AirportDto> toAirportDto(List<Airport> airports);

    AirportDto toAirportDto(Airport airport);

}
