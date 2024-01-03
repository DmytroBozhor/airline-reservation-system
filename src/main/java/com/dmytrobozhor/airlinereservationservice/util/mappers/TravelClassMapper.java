package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelClassMapper {

    @Mappings({@Mapping(source = "travelClassDto.name", target = "name")})
    TravelClass toTravelClass(TravelClassDto travelClassDto);

    @Mappings({@Mapping(source = "travelClassDto.name", target = "name")})
    TravelClass toTravelClass(TravelClassUpdateDto travelClassDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTravelClassPartial(@MappingTarget TravelClass persistedTravelClass, TravelClass travelClass);

    @Mappings(@Mapping(source = "travelClass.name", target = "name"))
    List<TravelClassDto> toTravelClassDto(List<TravelClass> travelClasses);

    @Mappings(@Mapping(source = "travelClass.name", target = "name"))
    TravelClassDto toTravelClassDto(TravelClass travelClass);

}
