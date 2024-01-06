package com.dmytrobozhor.airlinereservationservice.util.mappers;

import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ReservationSaveDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation toReservation(ReservationDto reservationDto);

    Reservation toReservation(ReservationSaveDto reservationDto);

    Reservation toReservation(ReservationPartialUpdateDto reservationDto);

    //    TODO: change the void to Reservation and refactor the service partial update method in all services
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReservationPartial(@MappingTarget Reservation persistedReservation, Reservation reservation);
//    Reservation updateReservationPartial(@MappingTarget Reservation persistedReservation, Reservation reservation);

    List<ReservationDto> toReservationDto(List<Reservation> reservations);

    ReservationDto toReservationDto(Reservation reservation);

}
