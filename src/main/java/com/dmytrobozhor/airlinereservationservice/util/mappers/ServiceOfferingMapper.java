package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceOfferingMapper {

    ServiceOffering toServiceOffering(ServiceOfferingDto serviceOfferingDto);

    ServiceOffering toServiceOffering(ServiceOfferingUpdateDto serviceOfferingDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateServiceOfferingPartial(
            @MappingTarget ServiceOffering persistedServiceOffering, ServiceOffering serviceOffering);

    List<ServiceOfferingDto> toServiceOfferingDto(List<ServiceOffering> serviceOfferings);

    ServiceOfferingDto toServiceOfferingDto(ServiceOffering serviceOffering);

}
