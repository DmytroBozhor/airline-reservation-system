package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.repository.FlightServiceRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightServiceMapper;
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
public class FlightServiceService implements AbstractFlightServiceService {

    private final FlightServiceRepository flightServiceRepository;

    private final FlightServiceMapper flightServiceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FlightService> findAll() {
        return flightServiceRepository.findAll();
    }

    @Override
    public FlightService save(FlightService flightService) {
        return flightServiceRepository.save(flightService);
    }

    @Override
    public void deleteById(Integer id) {
        FlightService flightService = flightServiceRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        flightServiceRepository.delete(flightService);
    }

    @Override
    public void delete(FlightService flightService) {
        FlightService persistedFlightService = flightServiceRepository
                .findByAllFields(flightService).orElseThrow(EntityNotFoundException::new);
        flightServiceRepository.delete(persistedFlightService);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightService findById(Integer id) {
        return flightServiceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightService updateById(Integer id, FlightService flightService) {
        return flightServiceRepository.findById(id).map(persistedFlightService -> {
            flightServiceMapper.updateFlightServicePartial(persistedFlightService, flightService);
            return flightServiceRepository.save(persistedFlightService);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightService updateOrCreateById(Integer id, FlightService flightService) {
        return flightServiceRepository.findById(id).map(persistedFlightService -> {
            flightServiceMapper.updateFlightServicePartial(persistedFlightService, flightService);
            return flightServiceRepository.save(persistedFlightService);
        }).orElseGet(() -> flightServiceRepository.save(flightService));
    }

}
