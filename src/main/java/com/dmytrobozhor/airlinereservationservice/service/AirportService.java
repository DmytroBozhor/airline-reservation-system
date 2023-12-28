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
@Slf4j
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

    //    TODO: throw EntityNotFoundException if entity does not exist
    @Override
    public void delete(Airport airport) {
        airportRepository.delete(airport);
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
        return airportRepository.findById(id).map(originalAirport -> {
            updateAirport(airport, originalAirport);
            return airportRepository.save(originalAirport);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport updateOrCreateById(Integer id, Airport airport) {
        return airportRepository.findById(id).map(originalAirport -> {
            updateAirport(airport, originalAirport);
            return airportRepository.save(originalAirport);
        }).orElse(airportRepository.save(airport));
    }

    private static void updateAirport(Airport airport, Airport originalAirport) {
        originalAirport.setName(airport.getName());
        originalAirport.setCity(airport.getCity());
        originalAirport.setCountry(airport.getCountry());
    }
}
