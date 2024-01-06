package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.SeatDetailSaveDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatDetailMapper {

    SeatDetail toSeatDetail(SeatDetailDto seatDetailDto);

    SeatDetail toSeatDetail(SeatDetailSaveDto seatDetailDto);

    SeatDetail toSeatDetail(SeatDetailPartialUpdateDto seatDetailUpdateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSeatDetailPartial(@MappingTarget SeatDetail persistedSeatDetail, SeatDetail seatDetail);

    List<SeatDetailDto> toSeatDetailDto(List<SeatDetail> seatDetails);

    SeatDetailDto toSeatDetailDto(SeatDetail seatDetail);

}
