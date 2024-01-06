package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassSaveDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassPartialUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelClassMapper {

    TravelClass toTravelClass(TravelClassDto travelClassDto);

    TravelClass toTravelClass(TravelClassSaveDto travelClassDto);

    TravelClass toTravelClass(TravelClassPartialUpdateDto travelClassDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTravelClassPartial(@MappingTarget TravelClass persistedTravelClass, TravelClass travelClass);

    List<TravelClassDto> toTravelClassDto(List<TravelClass> travelClasses);

    TravelClassDto toTravelClassDto(TravelClass travelClass);

}
