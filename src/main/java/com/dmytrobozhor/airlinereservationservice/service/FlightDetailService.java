package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import com.dmytrobozhor.airlinereservationservice.service.entity.manager.FlightDetailServiceEM;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FlightDetailService implements AbstractFlightDetailService {

    private final FlightDetailRepository flightDetailRepository;

    private final AirportRepository airportRepository;

    private final FlightDetailServiceEM flightDetailServiceEM;

    @Override
    @Transactional(readOnly = true)
    public List<FlightDetail> findAll() {
        return flightDetailRepository.findAll();
    }

    @SneakyThrows
    @Override
    public FlightDetail save(FlightDetail flightDetail) {
        /*var sourceAirportId = flightDetail.getSourceAirport().getId();
        var sourceAirport = airportRepository.findById(sourceAirportId)
                .orElseThrow(() -> new EntityNotFoundException("No source airport found by id" + sourceAirportId));

        var destinationAirportId = flightDetail.getDestinationAirport().getId();
        var destinationAirport = airportRepository.findById(destinationAirportId)
                .orElseThrow(() -> new EntityNotFoundException("No destination airport found by id" + destinationAirportId));

        flightDetail.setSourceAirport(sourceAirport);
        flightDetail.setDestinationAirport(destinationAirport);*/
        return flightDetailRepository.save(flightDetail);
    }

    @Override
    public FlightDetail deleteById(Long id) {
        var flightDetail = flightDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        flightDetailRepository.delete(flightDetail);
        return flightDetail;
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
                .map(persistedFlightDetail -> {
                    flightDetail.setId(id);
                    return flightDetailServiceEM.update(flightDetail);
                }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightDetail updateOrCreateById(Long id, FlightDetail flightDetail) {
        return flightDetailRepository.findById(id)
                .map(persistedFlightDetail -> {
                    flightDetail.setId(id);
                    return flightDetailServiceEM.update(flightDetail);
                }).orElseGet(() -> flightDetailServiceEM.save(flightDetail));
    }

    @Override
    public List<FlightDetail> saveAll(List<FlightDetail> flightDetails) {
        return flightDetailRepository.saveAll(flightDetails);
    }

}
