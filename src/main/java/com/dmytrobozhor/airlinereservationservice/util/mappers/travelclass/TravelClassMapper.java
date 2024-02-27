package com.dmytrobozhor.airlinereservationservice.util.mappers.travelclass;

import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.TravelClassPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.travelclass.TravelClassMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface TravelClassMapper extends UpdatePartiallyMapper<TravelClass, Long> {

    TravelClass toTravelClass(TravelClassReadDto travelClassDto);

    TravelClass toTravelClass(TravelClassCreateDto travelClassDto);

    TravelClass toTravelClass(TravelClassPartialUpdateDto travelClassDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = SEAT_DETAILS, ignore = true)
    @Mapping(target = SERVICE_OFFERINGS, ignore = true)
    TravelClass updatePartially(@MappingTarget TravelClass persistedTravelClass, TravelClass travelClass);

    List<TravelClassReadDto> toTravelClassDto(List<TravelClass> travelClasses);

    TravelClassReadDto toTravelClassDto(TravelClass travelClass);

}
