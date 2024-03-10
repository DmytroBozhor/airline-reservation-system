package com.dmytrobozhor.airlinereservationservice.service;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.repository.ServiceOfferingRepository;
import com.dmytrobozhor.airlinereservationservice.service.base.ServiceBase;
import com.dmytrobozhor.airlinereservationservice.util.mappers.serviceoffering.ServiceOfferingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ServiceOfferingService extends ServiceBase<ServiceOffering, Long> {

    private final ServiceOfferingRepository serviceOfferingRepository;

    private final ServiceOfferingMapper serviceOfferingMapper;

    @Autowired
    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository, ServiceOfferingMapper serviceOfferingMapper) {
        super(serviceOfferingRepository, serviceOfferingMapper);
        this.serviceOfferingRepository = serviceOfferingRepository;
        this.serviceOfferingMapper = serviceOfferingMapper;
    }
}
