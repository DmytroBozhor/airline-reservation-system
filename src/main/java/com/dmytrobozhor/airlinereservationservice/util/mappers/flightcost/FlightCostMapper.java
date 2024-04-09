package com.dmytrobozhor.airlinereservationservice.util.mappers.flightcost;

import com.dmytrobozhor.airlinereservationservice.domain.FlightCost;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightCostCreateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMappingConfig.class)
public interface FlightCostMapper extends UpdatePartiallyMapper<FlightCost, Long> {

    FlightCost toFlightCost(FlightCostReadDto flightCostDto);

    FlightCost toFlightCost(FlightCostCreateDto flightCostDto);

    FlightCost toFlightCost(FlightCostPartialUpdateDto flightCostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    FlightCost updatePartially(@MappingTarget FlightCost persistedFlightCost, FlightCost flightCost);

    List<FlightCostReadDto> toFlightCostDto(List<FlightCost> flightCosts);

    FlightCostReadDto toFlightCostDto(FlightCost flightCost);

}
