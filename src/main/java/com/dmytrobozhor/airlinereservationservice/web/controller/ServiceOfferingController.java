package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractServiceOfferingService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ServiceOfferingMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-offerings")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final AbstractServiceOfferingService serviceOfferingService;

    private final ServiceOfferingMapper serviceOfferingMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceOfferingDto> getAllServiceOfferings() {
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOfferingDto saveServiceOffering(
            @RequestBody @Valid ServiceOfferingDto serviceOfferingDto) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(
                serviceOfferingService.save(serviceOffering));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingDto getServiceOffering(@PathVariable Integer id) {
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceOfferingById(@PathVariable Integer id) {
        serviceOfferingService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingDto updateServiceOffering(
            @RequestBody @Valid ServiceOfferingUpdateDto serviceOfferingDto, @PathVariable Integer id) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(
                serviceOfferingService.updateById(id, serviceOffering));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingDto updateOrCreateServiceOffering(
            @RequestBody @Valid ServiceOfferingDto serviceOfferingDto,
            @PathVariable Integer id) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(
                serviceOfferingService.updateOrCreateById(id, serviceOffering));
    }

}
