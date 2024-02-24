package com.dmytrobozhor.airlinereservationservice.util.mappers.airport;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.airport.AirportMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface AirportMapper {

    Airport toAirport(AirportDto airportDto);

    Airport toAirport(AirportSaveDto airportDto);

    Airport toAirport(AirportPartialUpdateDto airportDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = FLIGHT_DETAILS_AS_SOURCE, ignore = true)
    @Mapping(target = FLIGHT_DETAILS_AS_DESTINATION, ignore = true)
    Airport updateAirportPartially(@MappingTarget Airport persistedAirport, Airport airport);

    List<AirportDto> toAirportDto(List<Airport> airports);

    AirportDto toAirportDto(Airport airport);

}
