package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportPartialUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    Airport toAirport(AirportDto airportDto);

    Airport toAirport(AirportSaveDto airportDto);

    Airport toAirport(AirportPartialUpdateDto airportDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAirportPartial(@MappingTarget Airport persistedAirport, Airport airport);

    List<AirportDto> toAirportDto(List<Airport> airports);

    AirportDto toAirportDto(Airport airport);

}
