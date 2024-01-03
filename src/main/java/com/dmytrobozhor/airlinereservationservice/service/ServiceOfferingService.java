package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.repository.ServiceOfferingRepository;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ServiceOfferingMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceOfferingService implements AbstractServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    private final ServiceOfferingMapper serviceOfferingMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ServiceOffering> findAll() {
        return serviceOfferingRepository.findAll();
    }

    @Override
    public ServiceOffering save(ServiceOffering serviceOffering) {
        return serviceOfferingRepository.save(serviceOffering);
    }

    //    TODO: change id type from integer to ServiceOfferingId
    @Override
    public void deleteById(Integer id) {
        ServiceOffering serviceOffering = serviceOfferingRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        serviceOfferingRepository.delete(serviceOffering);
    }

    @Override
    public void delete(ServiceOffering serviceOffering) {
        ServiceOffering persistedServiceOffering = serviceOfferingRepository
                .findByAllFields(serviceOffering).orElseThrow(EntityNotFoundException::new);
        serviceOfferingRepository.delete(persistedServiceOffering);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceOffering findById(Integer id) {
        return serviceOfferingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ServiceOffering updateById(Integer id, ServiceOffering serviceOffering) {
        return serviceOfferingRepository.findById(id).map(persistedServiceOffering -> {
            serviceOfferingMapper.updateServiceOfferingPartial(persistedServiceOffering, serviceOffering);
            return serviceOfferingRepository.save(persistedServiceOffering);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ServiceOffering updateOrCreateById(Integer id, ServiceOffering serviceOffering) {
        return serviceOfferingRepository.findById(id).map(persistedServiceOffering -> {
            serviceOfferingMapper.updateServiceOfferingPartial(persistedServiceOffering, serviceOffering);
            return serviceOfferingRepository.save(persistedServiceOffering);
        }).orElse(serviceOfferingRepository.save(serviceOffering));
    }

}
