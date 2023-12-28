package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FlightDetailMapper {

    @Mappings({@Mapping(source = "flightDetailDto.airplaneType", target = "airplaneType")})
    FlightDetail toFlightDetail(FlightDetailDto flightDetailDto);

}
