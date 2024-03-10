package com.dmytrobozhor.airlinereservationservice.util.mappers.flightservice;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServicePartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceCreateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMappingConfig.class)
public interface FlightServiceMapper extends UpdatePartiallyMapper<FlightService, Long> {

    FlightService toFlightService(FlightServiceReadDto flightServiceDto);

    FlightService toFlightService(FlightServiceCreateDto flightServiceDto);

    FlightService toFlightService(FlightServicePartialUpdateDto flightServiceDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serviceOfferings", ignore = true)
    FlightService updatePartially(@MappingTarget FlightService persistedFlightService, FlightService flightService);

    List<FlightServiceReadDto> toFlightServiceDto(List<FlightService> flightServices);

    FlightServiceReadDto toFlightServiceDto(FlightService flightService);

}
