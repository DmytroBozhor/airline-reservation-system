package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import com.dmytrobozhor.airlinereservationservice.repository.TravelClassRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.TravelClassMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public TravelClass deleteById(Long id) {
        var travelClass = travelClassRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        travelClassRepository.delete(travelClass);
        return travelClass;
    }

    //    TODO: learn about @Transactional
    @Override
    @Transactional(readOnly = true)
    public TravelClass findById(Long id) {
        return travelClassRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TravelClass updateById(Long id, TravelClass travelClass) {
        return travelClassRepository.findById(id).map(persistedTravelClass -> {
            travelClassMapper.updateTravelClassPartial(persistedTravelClass, travelClass);
            return travelClassRepository.save(persistedTravelClass);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TravelClass updateOrCreateById(Long id, TravelClass travelClass) {
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
