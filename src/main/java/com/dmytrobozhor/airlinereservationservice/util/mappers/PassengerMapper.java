package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    Passenger toPassenger(PassengerCreateDto passengerDto);

    Passenger toPassenger(PassengerUpdateDto passengerDto);

    Passenger toPassenger(PassengerDto passengerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePassengerPartial(@MappingTarget Passenger persitedPassenger, Passenger passenger);

    List<PassengerDto> toPassengerDto(List<Passenger> passengers);

    PassengerDto toPassengerDto(Passenger passenger);

}
