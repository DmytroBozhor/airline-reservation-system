package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Reservation;
import com.dmytrobozhor.airlinereservationservice.repository.ReservationRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ReservationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService implements AbstractReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Integer id) {
        Reservation reservation = reservationRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        reservationRepository.delete(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        Reservation persistedReservation = reservationRepository
                .findByAllFields(reservation).orElseThrow(EntityNotFoundException::new);
        reservationRepository.delete(persistedReservation);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation findById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Reservation updateById(Integer id, Reservation reservation) {
        return reservationRepository.findById(id).map(persistedReservation -> {
            reservationMapper.updateReservationPartial(persistedReservation, reservation);
            return reservationRepository.save(persistedReservation);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Reservation updateOrCreateById(Integer id, Reservation reservation) {
        return reservationRepository.findById(id).map(persistedReservation -> {
            reservationMapper.updateReservationPartial(persistedReservation, reservation);
            return reservationRepository.save(persistedReservation);
        }).orElseGet(() -> reservationRepository.save(reservation));
    }
}
