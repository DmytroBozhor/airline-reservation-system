package com.dmytrobozhor.airlinereservationservice.util.mappers.passenger;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerSaveDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.AbstractMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.passenger.PassengerMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface PassengerMapper {

    @PassengerEmbeddedComponentsMapping
    Passenger toPassenger(PassengerDto passengerDto);

    @PassengerEmbeddedComponentsMapping
    Passenger toPassenger(PassengerSaveDto passengerDto);

    @PassengerEmbeddedComponentsMapping
    Passenger toPassenger(PassengerPartialUpdateDto passengerDto);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @PassengerPartialUpdateMapping
    Passenger updatePassengerPartially(@MappingTarget Passenger persitedPassenger, Passenger passenger);

    List<PassengerDto> toPassengerDto(List<Passenger> passengers);

    @InheritInverseConfiguration
    PassengerDto toPassengerDto(Passenger passenger);

}
