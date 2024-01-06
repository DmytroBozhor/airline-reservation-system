package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.FlightCost;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightCostMapper {

    FlightCost toFlightCost(FlightCostDto flightCostDto);

    FlightCost toFlightCost(FlightCostUpdateDto flightCostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightCostPartial(@MappingTarget FlightCost persistedFlightCost, FlightCost flightCost);

    List<FlightCostDto> toFlightCostDto(List<FlightCost> flightCosts);

    FlightCostDto toFlightCostDto(FlightCost flightCost);

}
