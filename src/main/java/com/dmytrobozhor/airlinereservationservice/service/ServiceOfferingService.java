package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.repository.ServiceOfferingRepository;
import com.dmytrobozhor.airlinereservationservice.util.compositeid.ServiceOfferingId;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ServiceOfferingMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

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

    @Override
    public void deleteById(ServiceOfferingId id) {
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
    public ServiceOffering findById(ServiceOfferingId id) {
        return serviceOfferingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ServiceOffering updateById(ServiceOfferingId id, ServiceOffering serviceOffering) {
        return serviceOfferingRepository.findById(id).map(persistedServiceOffering -> {
            serviceOfferingMapper.updateServiceOfferingPartial(persistedServiceOffering, serviceOffering);
            return serviceOfferingRepository.save(persistedServiceOffering);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ServiceOffering updateOrCreateById(ServiceOfferingId id, ServiceOffering serviceOffering) {
        return serviceOfferingRepository.findById(id).map(persistedServiceOffering -> {
            serviceOfferingMapper.updateServiceOfferingPartial(persistedServiceOffering, serviceOffering);
            return serviceOfferingRepository.save(persistedServiceOffering);
        }).orElseGet(() -> serviceOfferingRepository.save(serviceOffering));
    }

    @Override
    public List<ServiceOffering> saveAll(List<ServiceOffering> serviceOfferings) {
        return serviceOfferingRepository.saveAll(serviceOfferings);
    }

}
