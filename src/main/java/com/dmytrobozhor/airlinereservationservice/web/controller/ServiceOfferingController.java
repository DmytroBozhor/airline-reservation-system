package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingReadDto;
import com.dmytrobozhor.airlinereservationservice.service.ServiceOfferingService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.serviceoffering.ServiceOfferingMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-offerings")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    private final ServiceOfferingMapper serviceOfferingMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceOfferingReadDto> getAllServiceOfferings() {
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOfferingReadDto saveServiceOffering(@RequestBody @Valid ServiceOfferingCreateDto serviceOfferingDto) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.save(serviceOffering));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingReadDto getServiceOfferingById(@PathVariable Long id) {
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingReadDto deleteServiceOfferingById(@PathVariable Long id) {
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingReadDto updateServiceOffering(
            @RequestBody @Valid ServiceOfferingPartialUpdateDto serviceOfferingDto, @PathVariable Long id) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.updateById(id, serviceOffering));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingReadDto updateOrCreateServiceOffering(
            @RequestBody @Valid ServiceOfferingCreateDto serviceOfferingDto, @PathVariable Long id) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.updateOrCreateById(id, serviceOffering));
    }

}
