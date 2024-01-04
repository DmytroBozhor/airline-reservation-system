package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.repository.PassengerRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PassengerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class PassengerService implements AbstractPassengerService {

    private final PassengerRepository passengerRepository;

    private final PassengerMapper passengerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public void deleteById(Integer id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        passengerRepository.delete(passenger);
    }

    @Override
    public void delete(Passenger passenger) {
        Passenger persistedPassenger = passengerRepository
                .findByAllFields(passenger).orElseThrow(EntityNotFoundException::new);
        passengerRepository.delete(persistedPassenger);
    }

    @Override
    @Transactional(readOnly = true)
    public Passenger findById(Integer id) {
        return passengerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Passenger updateById(Integer id, Passenger passenger) {
        return passengerRepository.findById(id).map(persistedPassenger -> {
            passengerMapper.updatePassengerPartial(persistedPassenger, passenger);
            return passengerRepository.save(persistedPassenger);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Passenger updateOrCreateById(Integer id, Passenger passenger) {
        return passengerRepository.findById(id).map(persistedPassenger -> {
            passengerMapper.updatePassengerPartial(persistedPassenger, passenger);
            return passengerRepository.save(persistedPassenger);
        }).orElseGet(() -> passengerRepository.save(passenger));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Passenger> findByPhoneNumber(String phoneNumber) {
        return passengerRepository.findPassengerByPhoneNumber(phoneNumber);
    }
}
