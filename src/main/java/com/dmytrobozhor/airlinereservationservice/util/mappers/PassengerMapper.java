package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.domain.embeddable.AdditionalInfo;
import com.dmytrobozhor.airlinereservationservice.domain.embeddable.PersonalInfo;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PassengerSaveDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

//    @Mappings(value = {
//            @Mapping(target = "personalInfo.firstName", source = "passengerDto.firstName"),
//            @Mapping(target = "personalInfo.lastName", source = "passengerDto.lastName"),
//            @Mapping(target = "personalInfo.phoneNumber", source = "passengerDto.phoneNumber"),
//            @Mapping(target = "additionalInfo.email", source = "passengerDto.email"),
//            @Mapping(target = "additionalInfo.address", source = "passengerDto.address"),
//            @Mapping(target = "additionalInfo.city", source = "passengerDto.city"),
//            @Mapping(target = "additionalInfo.state", source = "passengerDto.state"),
//            @Mapping(target = "additionalInfo.zipcode", source = "passengerDto.zipcode"),
//            @Mapping(target = "additionalInfo.country", source = "passengerDto.country")
//    })
    Passenger toPassenger(PassengerDto passengerDto);

    Passenger toPassenger(PassengerSaveDto passengerDto);

    Passenger toPassenger(PassengerPartialUpdateDto passengerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePassengerPartial(@MappingTarget Passenger persitedPassenger, Passenger passenger);

    List<PassengerDto> toPassengerDto(List<Passenger> passengers);

    PassengerDto toPassengerDto(Passenger passenger);

//    PersonalInfo toPersonalInfo(PassengerDto passengerDto);

//    AdditionalInfo toAdditionalInfo(PassengerDto passengerDto);

//    PersonalInfo toPersonalInfo(PassengerSaveDto passengerDto);

//    AdditionalInfo toAdditionalInfo(PassengerSaveDto passengerDto);

//    PersonalInfo toPersonalInfo(PassengerPartialUpdateDto passengerDto);

//    AdditionalInfo toAdditionalInfo(PassengerPartialUpdateDto passengerDto);

//    Passenger toPassenger(PersonalInfo personalInfo, AdditionalInfo additionalInfo);

//    Passenger toPassenger(PassengerDto passengerDto, PersonalInfo personalInfo, AdditionalInfo additionalInfo);

//    Passenger toPassenger(PassengerSaveDto passengerDto, PersonalInfo personalInfo, AdditionalInfo additionalInfo);

//    Passenger toPassenger(PassengerPartialUpdateDto passengerDto, PersonalInfo personalInfo, AdditionalInfo additionalInfo);

}
