package com.dmytrobozhor.airlinereservationservice.util.mappers.flight;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailSaveDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = FlightDetailAssociationMapper.class)
public interface FlightDetailMapper {

    FlightDetail toFlightDetail(FlightDetailDto flightDetailDto);

//    @BeanMapping(ignoreByDefault = true)
    @FlightDetailBaseMapping
    FlightDetail toFlightDetail(FlightDetailSaveDto flightDetailDto);

    FlightDetail toFlightDetail(FlightDetailPartialUpdateDto flightDetailDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightDetailPartial(@MappingTarget FlightDetail persistedFlightDetail, FlightDetail flightDetail);

    List<FlightDetailDto> toFlightDetailDto(List<FlightDetail> flightDetails);

    FlightDetailDto toFlightDetailDto(FlightDetail flightDetail);

}
