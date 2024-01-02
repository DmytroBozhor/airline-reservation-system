package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.PaymentStatus;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentStatusMapper {

    PaymentStatus toPaymentStatus(PaymentStatusDto paymentStatusDto);

    PaymentStatus toPaymentStatus(PaymentStatusUpdateDto paymentStatusDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentStatusPartial(
            @MappingTarget PaymentStatus persistedPaymentStatus, PaymentStatus paymentStatus);

    List<PaymentStatusDto> toPaymentStatusDto(List<PaymentStatus> paymentStatuses);

    PaymentStatusDto toPaymentStatusDto(PaymentStatus paymentStatusq);

}
