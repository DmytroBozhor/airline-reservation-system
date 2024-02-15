package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import com.dmytrobozhor.airlinereservationservice.service.entity.manager.FlightDetailServiceEM;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FlightDetailService implements AbstractFlightDetailService {

    private final FlightDetailRepository flightDetailRepository;

    private final FlightDetailServiceEM flightDetailServiceEM;

    @Override
    @Transactional(readOnly = true)
    public List<FlightDetail> findAll() {
        return flightDetailRepository.findAll();
    }

    @Override
    public FlightDetail save(FlightDetail flightDetail) {
        return flightDetailServiceEM.save(flightDetail);
    }

    @Override
    public FlightDetail deleteById(Long id) {
        var flightDetailOptional = flightDetailRepository.findById(id);
        flightDetailOptional.ifPresent(flightDetailRepository::delete);
        return flightDetailOptional.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDetail findById(Long id) {
        return flightDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightDetail updateById(Long id, FlightDetail flightDetail) {
        return flightDetailRepository.findById(id)
                .map(persistedFlightDetail ->
                        {
                            flightDetail.setId(id);
                            return flightDetailServiceEM.update(flightDetail);
                        }
                )
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightDetail updateOrCreateById(Long id, FlightDetail flightDetail) {
        return flightDetailRepository.findById(id)
                .map(persistedFlightDetail ->
                        {
                            flightDetail.setId(id);
                            return flightDetailServiceEM.update(flightDetail);
                        }
                )
                .orElseGet(() -> flightDetailServiceEM.save(flightDetail));
    }

    @Override
    public List<FlightDetail> saveAll(List<FlightDetail> flightDetails) {
        return flightDetailRepository.saveAll(flightDetails);
    }

}
