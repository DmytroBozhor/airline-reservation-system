package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
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

    private final AirportRepository airportRepository;

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

    @Override
    @Transactional(readOnly = true)
    public void fetchDataIfExist(FlightDetail flightDetail) {

        Optional<Airport> sourceAirportOptional = Optional.ofNullable(flightDetail.getSourceAirport());
        sourceAirportOptional.ifPresent(sourceAirport -> {
            Optional<Airport> persistedAirportOptional = airportRepository.findByAllFields(sourceAirport);
            persistedAirportOptional.ifPresent(persistedAirport -> {
                log.debug("The source airport already exists. Fetching...");
                flightDetail.setSourceAirport(persistedAirport);
            });
        });

        Optional<Airport> destinationAirportOptional = Optional.ofNullable(flightDetail.getDestinationAirport());
        destinationAirportOptional.ifPresent(destinationAirport -> {
            Optional<Airport> persistedAirportOptional = airportRepository.findByAllFields(destinationAirport);
            persistedAirportOptional.ifPresent(persistedAirport -> {
                log.debug("The destination airport already exists. Fetching...");
                flightDetail.setDestinationAirport(persistedAirport);
            });
        });

    }

}
