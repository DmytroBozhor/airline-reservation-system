package com.dmytrobozhor.airlinereservationservice.util.mappers.paymentstatus;

import com.dmytrobozhor.airlinereservationservice.domain.PaymentStatus;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusCreateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMappingConfig.class)
public interface PaymentStatusMapper extends UpdatePartiallyMapper<PaymentStatus, Long> {

    @Mapping(target = "reservation", source = "reservationId")
    PaymentStatus toPaymentStatus(PaymentStatusReadDto paymentStatusDto);

    @Mapping(target = "reservation", source = "reservationId")
    PaymentStatus toPaymentStatus(PaymentStatusCreateDto paymentStatusDto);

    @Mapping(target = "reservation", source = "reservationId")
    PaymentStatus toPaymentStatus(PaymentStatusPartialUpdateDto paymentStatusDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    PaymentStatus updatePartially(@MappingTarget PaymentStatus persistedPaymentStatus, PaymentStatus paymentStatus);

    List<PaymentStatusReadDto> toPaymentStatusDto(List<PaymentStatus> paymentStatuses);

    @InheritInverseConfiguration
    PaymentStatusReadDto toPaymentStatusDto(PaymentStatus paymentStatus);

}
