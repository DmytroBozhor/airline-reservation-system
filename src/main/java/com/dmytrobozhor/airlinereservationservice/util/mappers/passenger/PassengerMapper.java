package com.dmytrobozhor.airlinereservationservice.util.mappers.passenger;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMappingConfig.class)
public interface PassengerMapper extends UpdatePartiallyMapper<Passenger, Long> {

    @PassengerEmbeddedComponentsMapping
    Passenger toPassenger(PassengerReadDto passengerDto);

    @PassengerEmbeddedComponentsMapping
    Passenger toPassenger(PassengerCreateDto passengerDto);

    @PassengerEmbeddedComponentsMapping
    Passenger toPassenger(PassengerPartialUpdateDto passengerDto);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @PassengerPartialUpdateMapping
    Passenger updatePartially(@MappingTarget Passenger persitedPassenger, Passenger passenger);

    List<PassengerReadDto> toPassengerDto(List<Passenger> passengers);

    @InheritInverseConfiguration
    PassengerReadDto toPassengerDto(Passenger passenger);

}
