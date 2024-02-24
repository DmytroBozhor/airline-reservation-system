package com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailSaveDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.flightdetail.FlightDetailMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface FlightDetailMapper {

    @AssociationFromIdToEntity
    FlightDetail toFlightDetail(FlightDetailDto flightDetailDto);

    @AssociationFromIdToEntity
    FlightDetail toFlightDetail(FlightDetailSaveDto flightDetailDto);

    @AssociationFromIdToEntity
    FlightDetail toFlightDetail(FlightDetailPartialUpdateDto flightDetailDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = SEAT_DETAILS, ignore = true)
    FlightDetail updateFlightDetailPartially(@MappingTarget FlightDetail persistedFlightDetail, FlightDetail flightDetail);

    List<FlightDetailDto> toFlightDetailDto(List<FlightDetail> flightDetails);

    @InheritInverseConfiguration
    FlightDetailDto toFlightDetailDto(FlightDetail flightDetail);

}
