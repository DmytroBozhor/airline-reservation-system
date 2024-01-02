package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightServiceUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightServiceMapper {

    FlightService toFlightService(FlightServiceDto flightServiceDto);

    FlightService toFlightService(FlightServiceUpdateDto flightServiceDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightServicePartial(@MappingTarget FlightService persistedFlightService, FlightService flightService);

    List<FlightServiceDto> toFlightServiceDto(List<FlightService> flightServices);

    FlightServiceDto toFlightServiceDto(FlightService flightService);

}
