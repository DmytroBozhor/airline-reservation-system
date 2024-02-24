package com.dmytrobozhor.airlinereservationservice.util.mappers.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = AssociationMapper.class)
public interface CentralMappingConfig {
}
