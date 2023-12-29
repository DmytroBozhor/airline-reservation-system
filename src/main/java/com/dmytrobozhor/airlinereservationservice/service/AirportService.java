package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService implements AbstractAirportService {

    private final AirportRepository airportRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public void deleteById(Integer id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        airportRepository.delete(airport);
    }

    @Override
    public void delete(Airport airport) {
        Airport persistedAirport = airportRepository
                .findByAllFields(airport).orElseThrow(EntityNotFoundException::new);
        airportRepository.delete(persistedAirport);
    }

    @Override
    @Transactional(readOnly = true)
    public Airport findById(Integer id) {
        return airportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    //    TODO: make an opportunity to make partial updates if some of the properties are null
    @Override
    public Airport updateById(Integer id, Airport airport) {
        return airportRepository.findById(id).map(persistedAirport -> {
            updateAirport(airport, persistedAirport);
            return airportRepository.save(persistedAirport);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport updateOrCreateById(Integer id, Airport airport) {
        return airportRepository.findById(id).map(persistedAirport -> {
            updateAirport(airport, persistedAirport);
            return airportRepository.save(persistedAirport);
        }).orElse(airportRepository.save(airport));
    }

    private void updateAirport(Airport airport, Airport originalAirport) {
        originalAirport.setName(airport.getName());
        originalAirport.setCity(airport.getCity());
        originalAirport.setCountry(airport.getCountry());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Airport> findByAllFields(Airport airport) {
        return airportRepository.findByAllFields(airport);
    }
}
