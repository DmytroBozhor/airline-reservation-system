package com.dmytrobozhor.airlinereservationservice.util.mappers.airport;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.airport.AirportMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface AirportMapper extends UpdatePartiallyMapper<Airport, Long> {

    Airport toAirport(AirportReadDto airportDto);

    Airport toAirport(AirportCreateDto airportDto);

    Airport toAirport(AirportPartialUpdateDto airportDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = FLIGHT_DETAILS_AS_SOURCE, ignore = true)
    @Mapping(target = FLIGHT_DETAILS_AS_DESTINATION, ignore = true)
    Airport updatePartially(@MappingTarget Airport persistedAirport, Airport airport);

    List<AirportReadDto> toAirportDto(List<Airport> airports);

    AirportReadDto toAirportDto(Airport airport);

}
