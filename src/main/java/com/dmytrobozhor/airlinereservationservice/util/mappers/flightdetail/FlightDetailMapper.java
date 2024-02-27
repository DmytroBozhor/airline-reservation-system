package com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail.FlightDetailMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface FlightDetailMapper extends UpdatePartiallyMapper<FlightDetail, Long> {

    @FlightDetailAssociationMapping
    FlightDetail toFlightDetail(FlightDetailReadDto flightDetailDto);

    @FlightDetailAssociationMapping
    FlightDetail toFlightDetail(FlightDetailCreateDto flightDetailDto);

    @FlightDetailAssociationMapping
    FlightDetail toFlightDetail(FlightDetailPartialUpdateDto flightDetailDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = SEAT_DETAILS, ignore = true)
    FlightDetail updatePartially(@MappingTarget FlightDetail persistedFlightDetail, FlightDetail flightDetail);

    List<FlightDetailReadDto> toFlightDetailDto(List<FlightDetail> flightDetails);

    @InheritInverseConfiguration
    FlightDetailReadDto toFlightDetailDto(FlightDetail flightDetail);

}
