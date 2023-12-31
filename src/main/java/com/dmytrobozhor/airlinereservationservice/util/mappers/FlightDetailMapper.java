package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightDetailMapper {


    //    TODO: fix the repeating mappings
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

    //    TODO: find out how to map lists
    @Mappings({
            @Mapping(source = "flightDetail.airplaneType", target = "airplaneType"),
            @Mapping(source = "flightDetail.sourceAirport", target = "sourceAirport"),
            @Mapping(source = "flightDetail.destinationAirport", target = "destinationAirport")})
    List<FlightDetailDto> toFlightDetailDto(List<FlightDetail> flightDetails);

    @Mappings({
            @Mapping(source = "flightDetail.airplaneType", target = "airplaneType"),
            @Mapping(source = "flightDetail.sourceAirport", target = "sourceAirport"),
            @Mapping(source = "flightDetail.destinationAirport", target = "destinationAirport")})
    FlightDetailDto toFlightDetailDto(FlightDetail flightDetail);

}
