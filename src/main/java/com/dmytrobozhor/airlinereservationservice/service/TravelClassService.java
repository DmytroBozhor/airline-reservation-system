package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.TravelClassRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.TravelClassMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelClassService implements AbstractTravelClassService {

    private final TravelClassRepository travelClassRepository;

    private final TravelClassMapper travelClassMapper;

    //    TODO: should throw exception if no entity was found
    @Override
    @Transactional(readOnly = true)
    public List<TravelClass> findAll() {
        return travelClassRepository.findAll();
    }

    @Override
    public TravelClass save(TravelClass travelClass) {
        return travelClassRepository.save(travelClass);
    }

    @Override
    public void deleteById(Integer id) {
        TravelClass travelClass = travelClassRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        travelClassRepository.delete(travelClass);
    }

    @Override
    public void delete(TravelClass travelClass) {
        TravelClass persistedTravelClass = travelClassRepository
                .findByAllFields(travelClass).orElseThrow(EntityNotFoundException::new);
        travelClassRepository.delete(persistedTravelClass);
    }

    //    TODO: learn about @Transactional
    @Override
    @Transactional(readOnly = true)
    public TravelClass findById(Integer id) {
        return travelClassRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TravelClass updateById(Integer id, TravelClass travelClass) {
        return travelClassRepository.findById(id).map(persistedTravelClass -> {
            travelClassMapper.updateTravelClassPartial(persistedTravelClass, travelClass);
            return travelClassRepository.save(persistedTravelClass);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TravelClass updateOrCreateById(Integer id, TravelClass travelClass) {
        return travelClassRepository.findById(id).map(persistedTravelClass -> {
            travelClassMapper.updateTravelClassPartial(persistedTravelClass, travelClass);
            return travelClassRepository.save(persistedTravelClass);
        }).orElseGet(() -> travelClassRepository.save(travelClass));
    }

    @Override
    public List<TravelClass> saveAll(List<TravelClass> travelClasses) {
        return travelClassRepository.saveAll(travelClasses);
    }
}
