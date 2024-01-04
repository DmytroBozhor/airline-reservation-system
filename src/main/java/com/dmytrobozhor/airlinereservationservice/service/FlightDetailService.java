package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FlightDetailService implements AbstractFlightDetailService {

    private final FlightDetailRepository flightDetailRepository;

    private final FlightDetailMapper flightDetailMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FlightDetail> findAll() {
        return flightDetailRepository.findAll();
    }

    @Override
    public FlightDetail save(FlightDetail flightDetail) {
        return flightDetailRepository.save(flightDetail);
    }

    @Override
    public void deleteById(Integer id) {
        FlightDetail flightDetail = flightDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        flightDetailRepository.delete(flightDetail);
    }

    @Override
    public void delete(FlightDetail flightDetail) {
        FlightDetail persistedFlightDetail = flightDetailRepository
                .findByAllFields(flightDetail).orElseThrow(EntityNotFoundException::new);
        flightDetailRepository.delete(persistedFlightDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDetail findById(Integer id) {
        return flightDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightDetail updateById(Integer id, FlightDetail flightDetail) {
        return flightDetailRepository.findById(id).map(persistedFlightDetail -> {
            flightDetailMapper.updateFlightDetailPartial(persistedFlightDetail, flightDetail);
            return flightDetailRepository.save(persistedFlightDetail);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightDetail updateOrCreateById(Integer id, FlightDetail flightDetail) {
        return flightDetailRepository.findById(id).map(persistedFlightDetail -> {
            flightDetailMapper.updateFlightDetailPartial(persistedFlightDetail, flightDetail);
            return flightDetailRepository.save(persistedFlightDetail);
        }).orElseGet(() -> flightDetailRepository.save(flightDetail));
    }

}
