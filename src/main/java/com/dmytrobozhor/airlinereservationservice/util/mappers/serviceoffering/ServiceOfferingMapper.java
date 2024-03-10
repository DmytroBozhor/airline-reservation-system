package com.dmytrobozhor.airlinereservationservice.util.mappers.serviceoffering;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingCreateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMappingConfig.class)
public interface ServiceOfferingMapper extends UpdatePartiallyMapper<ServiceOffering, Long> {

    @ServiceOfferingAssociationMapper
    ServiceOffering toServiceOffering(ServiceOfferingReadDto serviceOfferingDto);

    @ServiceOfferingAssociationMapper
    ServiceOffering toServiceOffering(ServiceOfferingCreateDto serviceOfferingDto);

    @ServiceOfferingAssociationMapper
    ServiceOffering toServiceOffering(ServiceOfferingPartialUpdateDto serviceOfferingDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    ServiceOffering updatePartially(@MappingTarget ServiceOffering persistedServiceOffering,
                                    ServiceOffering serviceOffering);

    List<ServiceOfferingReadDto> toServiceOfferingDto(List<ServiceOffering> serviceOfferings);

    @InheritInverseConfiguration
    ServiceOfferingReadDto toServiceOfferingDto(ServiceOffering serviceOffering);

}
