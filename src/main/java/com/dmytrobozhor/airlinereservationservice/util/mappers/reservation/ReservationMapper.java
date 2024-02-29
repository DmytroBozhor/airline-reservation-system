package com.dmytrobozhor.airlinereservationservice.util.mappers.reservation;

import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationCreateDto;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.CentralMappingConfig;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import org.mapstruct.*;

import java.util.List;

import static com.dmytrobozhor.airlinereservationservice.util.mappers.reservation.ReservationMappingConstants.*;

@Mapper(config = CentralMappingConfig.class)
public interface ReservationMapper extends UpdatePartiallyMapper<Reservation, Long> {

    @ReservationAssociationMapper
    Reservation toReservation(ReservationReadDto reservationDto);

    @ReservationAssociationMapper
    Reservation toReservation(ReservationCreateDto reservationDto);

    @ReservationAssociationMapper
    Reservation toReservation(ReservationPartialUpdateDto reservationDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = ID, ignore = true)
    @Mapping(target = PAYMENT_STATUSES, ignore = true)
    Reservation updatePartially(@MappingTarget Reservation persistedReservation, Reservation reservation);

    List<ReservationReadDto> toReservationDto(List<Reservation> reservations);

    @InheritInverseConfiguration
    ReservationReadDto toReservationDto(Reservation reservation);

}
