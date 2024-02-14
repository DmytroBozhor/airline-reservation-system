package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
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
        var airport = airportRepository.findById(id);
        airport.ifPresent(airportRepository::delete);
        return airport.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Airport findById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport updateById(Long id, Airport airport) {
        return airportRepository.findById(id).map(persistedAirport -> {
            airportMapper.updateAirportPartial(persistedAirport, airport);
            return airportRepository.save(persistedAirport);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport updateOrCreateById(Long id, Airport airport) {
        return airportRepository.findById(id).map(persistedAirport -> {
            airportMapper.updateAirportPartial(persistedAirport, airport);
            return airportRepository.save(persistedAirport);
        }).orElseGet(() -> airportRepository.save(airport));
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Airport> findByAllFields(Airport airport) {
//        return airportRepository.findByAllFields(airport);
//    }

    @Override
    public List<Airport> saveAll(List<Airport> airportsForSave) {
        return airportRepository.saveAll(airportsForSave);
    }

    @Override
    public void deleteAll() {
        airportRepository.deleteAll();
    }
}
