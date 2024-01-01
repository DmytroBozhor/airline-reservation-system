package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatDetailMapper {

    //    TODO: check if works without mappings. if doesnt - map fields
    SeatDetail toSeatDetail(SeatDetailDto seatDetailDto);

    SeatDetail toSeatDetail(SeatDetailUpdateDto seatDetailUpdateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSeatDetailPartial(@MappingTarget SeatDetail persistedSeatDetail, SeatDetail seatDetail);

    List<SeatDetailDto> toSeatDetailDto(List<SeatDetail> seatDetails);

    SeatDetailDto toSeatDetailDto(SeatDetail seatDetail);

}
