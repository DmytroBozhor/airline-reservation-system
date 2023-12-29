package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightDetailService implements AbstractFlightDetailService {

    private final FlightDetailRepository flightDetailRepository;


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
                .findByAllFields(flightDetail).orElseThrow(EmptyStackException::new);
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
        return flightDetailRepository.findById(id).map(originalFlightDetail -> {
            updateFlightDetail(flightDetail, originalFlightDetail);
            return flightDetailRepository.save(originalFlightDetail);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlightDetail updateOrCreateById(Integer id, FlightDetail flightDetail) {
        return flightDetailRepository.findById(id).map(originalFlightDetail -> {
            updateFlightDetail(flightDetail, originalFlightDetail);
            return flightDetailRepository.save(originalFlightDetail);
        }).orElse(flightDetailRepository.save(flightDetail));
    }

    private void updateFlightDetail(FlightDetail flightDetail, FlightDetail originalFlightDetail) {
        originalFlightDetail.setDepartureDateTime(flightDetail.getDepartureDateTime());
        originalFlightDetail.setArrivalDateTime(flightDetail.getArrivalDateTime());
        originalFlightDetail.setAirplaneType(flightDetail.getAirplaneType());
        originalFlightDetail.setSourceAirport(flightDetail.getSourceAirport());
        originalFlightDetail.setDestinationAirport(flightDetail.getDestinationAirport());
    }

}
