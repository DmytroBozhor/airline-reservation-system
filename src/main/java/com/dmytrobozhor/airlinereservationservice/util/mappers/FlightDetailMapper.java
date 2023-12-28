package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightDetailMapper {

    FlightDetail toFlightDetail(FlightDetailDto flightDetailDto);

}
