package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerSaveDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    Passenger toPassenger(PassengerDto passengerDto);

    Passenger toPassenger(PassengerSaveDto passengerDto);

    Passenger toPassenger(PassengerPartialUpdateDto passengerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePassengerPartial(@MappingTarget Passenger persitedPassenger, Passenger passenger);

    List<PassengerDto> toPassengerDto(List<Passenger> passengers);

    PassengerDto toPassengerDto(Passenger passenger);

}
