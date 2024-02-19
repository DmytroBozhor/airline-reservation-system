package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import com.dmytrobozhor.airlinereservationservice.exceptions.EntityUniquePhoneNumberException;
import com.dmytrobozhor.airlinereservationservice.repository.PassengerRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PassengerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        if (passengerRepository.findPassengerByPhoneNumber(passenger.getPhoneNumber()).isPresent()) {
            throw new EntityUniquePhoneNumberException("Passenger with a phone number "
                    + passenger.getPhoneNumber() + " already exists");
        }
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger deleteById(Long id) {
        var passenger = passengerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        passengerRepository.delete(passenger);
        return passenger;
    }

    @Override
    @Transactional(readOnly = true)
    public Passenger findById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Passenger updateById(Long id, Passenger passenger) {
        return passengerRepository.findById(id).map(persistedPassenger -> {
            passengerMapper.updatePassengerPartial(persistedPassenger, passenger);
            return passengerRepository.save(persistedPassenger);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Passenger updateOrCreateById(Long id, Passenger passenger) {
        if (passengerRepository.findPassengerByPhoneNumber(passenger.getPhoneNumber()).isPresent()) {
            throw new EntityUniquePhoneNumberException("Passenger with a phone number "
                    + passenger.getPhoneNumber() + " already exists");
        }
        return passengerRepository.findById(id).map(persistedPassenger -> {
            passengerMapper.updatePassengerPartial(persistedPassenger, passenger);
            return passengerRepository.save(persistedPassenger);
        }).orElseGet(() -> passengerRepository.save(passenger));
    }

    @Override
    public List<Passenger> saveAll(List<Passenger> passengers) {
        return passengerRepository.saveAll(passengers);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Passenger> findByPhoneNumber(String phoneNumber) {
        return passengerRepository.findPassengerByPhoneNumber(phoneNumber);
    }
}
