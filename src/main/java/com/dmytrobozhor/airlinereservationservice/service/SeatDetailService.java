package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.FlightDetailRepository;
import com.dmytrobozhor.airlinereservationservice.repository.SeatDetailRepository;
import com.dmytrobozhor.airlinereservationservice.repository.TravelClassRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.SeatDetailMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SeatDetailService implements AbstractSeatDetailService {

    private final SeatDetailRepository seatDetailRepository;

    private final SeatDetailMapper seatDetailMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SeatDetail> findAll() {
        return seatDetailRepository.findAll();
    }

    @Override
    public SeatDetail save(SeatDetail seatDetail) {
        return seatDetailRepository.save(seatDetail);
    }

    @Override
    public void deleteById(Integer id) {
        SeatDetail seatDetail = seatDetailRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        seatDetailRepository.delete(seatDetail);
    }

    @Override
    public void delete(SeatDetail seatDetail) {
        SeatDetail persistedSeatDetail = seatDetailRepository
                .findByAllFields(seatDetail).orElseThrow(EntityNotFoundException::new);
        seatDetailRepository.delete(persistedSeatDetail);
    }

    @Override
    public SeatDetail findById(Integer id) {
        return seatDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public SeatDetail updateById(Integer id, SeatDetail seatDetail) {
        return seatDetailRepository.findById(id).map(persistedSeatDetail -> {
            seatDetailMapper.updateSeatDetailPartial(persistedSeatDetail, seatDetail);
            return seatDetailRepository.save(persistedSeatDetail);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public SeatDetail updateOrCreateById(Integer id, SeatDetail seatDetail) {
        return seatDetailRepository.findById(id).map(persistedSeatDetail -> {
            seatDetailMapper.updateSeatDetailPartial(persistedSeatDetail, seatDetail);
            return seatDetailRepository.save(persistedSeatDetail);
        }).orElseGet(() -> seatDetailRepository.save(seatDetail));
    }

    @Override
    public List<SeatDetail> saveAll(List<SeatDetail> seatDetails) {
        return seatDetailRepository.saveAll(seatDetails);
    }

}
