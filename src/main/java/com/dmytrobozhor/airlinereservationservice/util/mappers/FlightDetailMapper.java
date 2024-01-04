package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightDetailMapper {

    FlightDetail toFlightDetail(FlightDetailDto flightDetailDto);

    FlightDetail toFlightDetail(FlightDetailUpdateDto flightDetailDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightDetailPartial(@MappingTarget FlightDetail persistedFlightDetail, FlightDetail flightDetail);

    List<FlightDetailDto> toFlightDetailDto(List<FlightDetail> flightDetails);

    FlightDetailDto toFlightDetailDto(FlightDetail flightDetail);

}
