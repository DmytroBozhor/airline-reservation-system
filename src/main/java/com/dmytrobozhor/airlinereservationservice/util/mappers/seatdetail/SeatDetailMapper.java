package com.dmytrobozhor.airlinereservationservice.util.mappers.seatdetail;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.seatdetail.SeatDetailMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface SeatDetailMapper extends UpdatePartiallyMapper<SeatDetail, Long> {

    @SeatDetailAssociationMapping
    SeatDetail toSeatDetail(SeatDetailReadDto seatDetailDto);

    @SeatDetailAssociationMapping
    SeatDetail toSeatDetail(SeatDetailCreateDto seatDetailDto);

    @SeatDetailAssociationMapping
    SeatDetail toSeatDetail(SeatDetailPartialUpdateDto seatDetailUpdateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = RESERVATIONS, ignore = true)
    @Mapping(target = FLIGHT_COSTS, ignore = true)
    SeatDetail updatePartially(@MappingTarget SeatDetail persistedSeatDetail, SeatDetail seatDetail);

    List<SeatDetailReadDto> toSeatDetailDto(List<SeatDetail> seatDetails);

    @InheritInverseConfiguration
    SeatDetailReadDto toSeatDetailDto(SeatDetail seatDetail);

}
