package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightDetailMapper {

    @Mappings({
            @Mapping(source = "flightDetailDto.airplaneType", target = "airplaneType"),
            @Mapping(source = "flightDetailDto.sourceAirport", target = "sourceAirport"),
            @Mapping(source = "flightDetailDto.destinationAirport", target = "destinationAirport")})
    FlightDetail toFlightDetail(FlightDetailDto flightDetailDto);

    @Mappings({
            @Mapping(source = "flightDetailDto.airplaneType", target = "airplaneType"),
            @Mapping(source = "flightDetailDto.sourceAirport", target = "sourceAirport"),
            @Mapping(source = "flightDetailDto.destinationAirport", target = "destinationAirport")})
    FlightDetail toFlightDetail(FlightDetailUpdateDto flightDetailDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightDetailPartial(@MappingTarget FlightDetail persistedFlightDetail, FlightDetail flightDetail);

    @Mappings({
            @Mapping(target = "flightDetailDto.airplaneType", source = "airplaneType"),
            @Mapping(target = "flightDetailDto.sourceAirport", source = "sourceAirport"),
            @Mapping(target = "flightDetailDto.destinationAirport", source = "destinationAirport")})
    List<FlightDetailDto> toFlightDetailDto(List<FlightDetail> flightDetails);

    @Mappings({
            @Mapping(target = "flightDetailDto.airplaneType", source = "airplaneType"),
            @Mapping(target = "flightDetailDto.sourceAirport", source = "sourceAirport"),
            @Mapping(target = "flightDetailDto.destinationAirport", source = "destinationAirport")})
    FlightDetailDto toFlightDetailDto(FlightDetail flightDetail);

}
