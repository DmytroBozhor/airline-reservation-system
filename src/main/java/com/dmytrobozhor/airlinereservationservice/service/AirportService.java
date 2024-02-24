package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.airport.AirportMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AirportService implements AbstractAirportService {

    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

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
    public Airport deleteById(Long id) {
        var airport = airportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        airportRepository.delete(airport);
        return airport;
    }

    @Override
    @Transactional(readOnly = true)
    public Airport findById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport updateById(Long id, Airport airport) {
        return airportRepository.findById(id)
                .map(persistedAirport -> airportMapper.updateAirportPartially(persistedAirport, airport))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport updateOrCreateById(Long id, Airport airport) {
        return airportRepository.findById(id)
                .map(persistedAirport -> airportMapper.updateAirportPartially(persistedAirport, airport))
                .orElseGet(() -> airportRepository.save(airport));
    }

    @Override
    public List<Airport> saveAll(List<Airport> airportsForSave) {
        return airportRepository.saveAll(airportsForSave);
    }

    @Override
    public void deleteAll() {
        airportRepository.deleteAll();
    }
}
