package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightCost;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.repository.FlightCostRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightCostMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightCostService implements AbstractFlightCostService {

    private final FlightCostRepository flightCostRepository;

    private final FlightCostMapper flightCostMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FlightCost> findAll() {
        return flightCostRepository.findAll();
    }

    @Override
    public FlightCost save(FlightCost flightCost) {
        return flightCostRepository.save(flightCost);
    }

    //    TODO: change integer to FlightCostId id
    @Override
    public void deleteById(Integer id) {
        FlightCost flightCost = flightCostRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        flightCostRepository.delete(flightCost);
    }

    @Override
    public void delete(FlightCost flightCost) {
        FlightCost persistedFlightCost = flightCostRepository
                .findByAllFields(flightCost).orElseThrow(EntityNotFoundException::new);
        flightCostRepository.delete(persistedFlightCost);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightCost findById(Integer id) {
        return flightCostRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightCost updateById(Integer id, FlightCost flightCost) {
        return flightCostRepository.findById(id).map(persistedFlightCost -> {
            flightCostMapper.updateFlightCostPartial(persistedFlightCost, flightCost);
            return flightCostRepository.save(persistedFlightCost);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightCost updateOrCreateById(Integer id, FlightCost flightCost) {
        return flightCostRepository.findById(id).map(persistedFlightCost -> {
            flightCostMapper.updateFlightCostPartial(persistedFlightCost, flightCost);
            return flightCostRepository.save(persistedFlightCost);
        }).orElseGet(() -> flightCostRepository.save(flightCost));
    }

}
